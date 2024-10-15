/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.AgregaChef;

/**
 *
 * @author Jason
 */
public class AgregarChef {
    
private String mUUID;

    public String getUUID() {
        return mUUID;
    }

    public void setUUID(String UUID) {
        this.mUUID = UUID;
    }
    private String nombre;
    private int peso;
    private int edad;
    private String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
        public void GuardarCo() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conecxion = Conexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conecxion.prepareStatement("insert into tbChef (UUID_Chef,Nombre_Chef,Edad_Chef,Peso_Chef,Correo_Chef) " +
            "values (?,?,?,?,?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setInt(3, getEdad());
            addProducto.setInt(4, getPeso());
            addProducto.setString(5, getCorreo());

            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conecxion = Conexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteCarpintero = conecxion.prepareStatement("delete from tbChef where UUID_Chef = ?");
            deleteCarpintero.setString(1, miId);
            deleteCarpintero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
        public void cargarDatosTabla(AgregaChef vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtChef.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtChef.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeCarp = vista.jtChef.getValueAt(filaSeleccionada, 1).toString();
            String EdadCarp = vista.jtChef.getValueAt(filaSeleccionada, 2).toString();
            String PesoCarp = vista.jtChef.getValueAt(filaSeleccionada, 3).toString();
            String CorreoCarp = vista.jtChef.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeCarp);
            vista.txtEdad.setText(EdadCarp);
            vista.txtPeso25.setText(PesoCarp);
            vista.txtCorreo25.setText(CorreoCarp);

        }
    }
             public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = Conexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Chef", "Nombre_Chef", "Edad_Chef", "Peso_Chef","Correo_Chef"});
        try {
            
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbChef");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Chef"), 
                    rs.getString("Nombre_Chef"), 
                    rs.getInt("Edad_Chef"), 
                    rs.getInt("Peso_Chef"),
                    rs.getString("Correo_Chef")
                       
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }    
              public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String mUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbChef set Nombre_Chef= ?,Edad_Chef= ?,Peso_Chef = ?,Correo_Chef=? where UUID_Chef = ?");
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setInt(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, mUUId);

                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    
}

