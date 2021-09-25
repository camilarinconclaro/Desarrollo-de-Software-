package restful.Model;

/**
 *
 * @author grupo8
 */
package restful.Model;
import java.util.ArrayList;
import java.sql.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductoModel {

    private int codigo_prod;
    private String nombre_prod;
    private String descripcion_prod;
    private double precio_venta_prod;
    private double precio_costo_prod;
    private int stock_prod;

    public ProductoModel() {
    }

    public ProductoModel(int codigo_prod, String nombre_prod, String descripcion_prod, double precio_venta_prod, double precio_costo_prod, int stock_prod) {
        this.codigo_prod = codigo_prod;
        this.nombre_prod = nombre_prod;
        this.descripcion_prod = descripcion_prod;
        this.precio_venta_prod = precio_venta_prod;
        this.precio_costo_prod = precio_costo_prod;
        this.stock_prod = stock_prod;
    }

    public int getCodigo_prod() {
        return codigo_prod;
    }

    public void setCodigo_prod(int codigo_prod) {
        this.codigo_prod = codigo_prod;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public String getDescripcion_prod() {
        return descripcion_prod;
    }

    public void setDescripcion_prod(String descripcion_prod) {
        this.descripcion_prod = descripcion_prod;
    }

    public double getPrecio_venta_prod() {
        return precio_venta_prod;
    }

    public void setPrecio_venta_prod(double precio_venta_prod) {
        this.precio_venta_prod = precio_venta_prod;
    }

    public double getPrecio_costo_prod() {
        return precio_costo_prod;
    }

    public void setPrecio_costo_prod(double precio_costo_prod) {
        this.precio_costo_prod = precio_costo_prod;
    }

    public int getStock_prod() {
        return stock_prod;
    }

    public void setStock_prod(int stock_prod) {
        this.stock_prod = stock_prod;
    }

}
