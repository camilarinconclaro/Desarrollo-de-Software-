const url = "http://localhost:8080/Tienda_barrio/api/productos"
const contenedor = document.querySelector('tbody')
let resultados = ''

const modalProductos = new bootstrap.Modal(document.getElementById('modalProducto'))
const formProductos = document.querySelector('form')
const nombreProducto = document.getElementById('nombre')
const descripcionProducto = document.getElementById('descripcion')
const precioVentaProducto = document.getElementById('precio de venta')
const precioCostoProducto = document.getElementById('precio de costo')
const stockProducto = document.getElementById('stock')
const codigoProducto = document.getElementById('codigo') 

let opcion = ''

btnCrear.addEventListener('click', () => {
    
    nombreProducto.value = ''
    descripcionProducto.value = ''
    precioVentaProducto.value = ''
    precioCostoProducto.value = ''
    stockProducto.value = ''
    codigoProducto.value = ''
    codigoProducto.disabled = false
    modalProductos.show()
    opcion = 'crear'
})


const ajax = (options) => {
    let { url, method, success, error, data } = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", (e) => {
        if (xhr.readyState !== 4) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            success(json);
        } else {
            let message = xhr.statusText || "Ocurrió un error";
            error(`Error ${xhr.status}: ${message}`);
        }
    });

    xhr.open(method || "GET", url);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
};

const getAll = () => {
    ajax({
        url: url,
        method: "GET",
        success: (res) => {
            console.log(res);
                       
            res.forEach((productos) => {  
                resultados += `<tr>  
                        <td width="10%">${productos.codigo_prod}</td> 
                        <td width="15%">${productos.nombre_prod}</td>
                        <td width="25%">${productos.descripcion_prod}</td>
                        <td width="15%">${productos.precio_venta_prod}</td>
                        <td width="15%">${productos.precio_costo_prod}</td>
                        <td width="15%">${productos.stock_prod}</td>
                        <td class="text-center" width="20%"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                    </tr>`
            });

            contenedor.innerHTML = resultados
        },
        error: (err) => {
            console.log(err);
            $table.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`);
        },
    });
};


document.addEventListener("DOMContentLoaded", getAll); 


document.addEventListener("click", (e) => {
                           
    if (e.target.matches(".btnBorrar")) {
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        console.log(id) 
        alertify.confirm(`¿Estás seguro de eliminar el código ${id}?`,
            function () {
                ajax({
                    url: url + "/" + id,
                    method: "DELETE",  
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Cancel')
            });


    }     
    if (e.target.matches(".btnEditar")) {
        const fila = e.target.parentNode.parentNode
        codigoProducto.value = fila.children[0].innerHTML
        nombreProducto.value  = fila.children[1].innerHTML
        descripcionProducto.value = fila.children[2].innerHTML
        precioVentaProducto.value = fila.children[3].innerHTML
        precioCostoProducto.value = fila.children[4].innerHTML
        stockProducto.value = fila.children[5].innerHTML
        codigoProducto.disabled = true
        opcion = 'editar'
        modalProductos.show()
    }
})


formProductos.addEventListener('submit', (e) => {
    e.preventDefault()
    let metodo = "POST"
    if (opcion == 'editar') {
        metodo = "PUT"
 
    }
    ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: { 
            "codigo_prod": codigoProducto.value,
            "descripcion_prod": descripcionProducto.value,
            "nombre_prod": nombreProducto.value,    
            "precio_costo_prod": precioCostoProducto.value,
            "precio_venta_prod": precioVentaProducto.value,
            "stock_prod": stockProducto.value
        },
    });
    modalProductos.hide()
})
