package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.ProductoModel;
import restful.Model.Conexion;

public class ProductoService {

    public ArrayList<ProductoModel> getProductos() {
        ArrayList<ProductoModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM producto";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ProductoModel producto = new ProductoModel();
                producto.setCodigo_prod(rs.getInt("codigo_prod"));
                producto.setNombre_prod(rs.getString("nombre_prod"));
                producto.setDescripcion_prod(rs.getString("descripcion_prod"));
                producto.setPrecio_venta_prod(rs.getDouble("precio_venta_prod"));
                producto.setPrecio_costo_prod(rs.getDouble("precio_costo_prod"));
                producto.setStock_prod(rs.getInt("stock_prod"));

                lista.add(producto);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public ProductoModel getProducto(int id) {
        ProductoModel producto = new ProductoModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM producto WHERE codigo_prod = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                producto.setCodigo_prod(rs.getInt("codigo_prod"));
                producto.setNombre_prod(rs.getString("nombre_prod"));
                producto.setDescripcion_prod(rs.getString("descripcion_prod"));
                producto.setPrecio_venta_prod(rs.getDouble("precio_venta_prod"));
                producto.setPrecio_costo_prod(rs.getDouble("precio_costo_prod"));
                producto.setStock_prod(rs.getInt("stock_prod"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return producto;
    }

    public ProductoModel addProducto(ProductoModel producto) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO producto(codigo_prod, nombre_prod, descripcion_prod, precio_venta_prod, precio_costo_prod, stock_prod)";
        Sql = Sql + "values (?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, producto.getCodigo_prod());
            pstm.setString(2, producto.getNombre_prod());
            pstm.setString(3, producto.getDescripcion_prod());
            pstm.setDouble(4, producto.getPrecio_venta_prod());
            pstm.setDouble(5, producto.getPrecio_costo_prod());
            pstm.setInt(6, producto.getStock_prod());

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return producto;
    }

    public ProductoModel updateProducto(ProductoModel producto) {
        Conexion conn = new Conexion();
        String sql = "UPDATE producto SET nombre_prod=?,descripcion_prod=?,precio_venta_prod=?,precio_costo_prod=?,stock_prod=? WHERE codigo_prod= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, producto.getNombre_prod());
            pstm.setString(2, producto.getDescripcion_prod());
            pstm.setDouble(3, producto.getPrecio_venta_prod());
            pstm.setDouble(4, producto.getPrecio_costo_prod());
            pstm.setInt(5, producto.getStock_prod());
            pstm.setInt(6, producto.getCodigo_prod());
            pstm.executeUpdate();

        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return null;
        }
        return producto;
    }

    public String delProducto(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM producto WHERE codigo_prod= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }

}
