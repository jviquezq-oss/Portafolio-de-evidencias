package LogicaDeNegocio;

import DAO.ClienteDAO;
import Entidades.Cliente;
import Excepciones.ExcepcionDeNegocio;

import java.util.ArrayList;

public class AdministradorClientes {

    private final ClienteDAO clienteDAO;

    public AdministradorClientes() {

        clienteDAO = new ClienteDAO();

    }

    public void registrarCliente(Cliente nuevoCliente) throws ExcepcionDeNegocio {

        if (nuevoCliente == null) {
            throw new ExcepcionDeNegocio("El cliente no puede ser nulo.");
        }

        if (clienteDAO.buscar(nuevoCliente.getIdentificacion()) != null) {
            throw new ExcepcionDeNegocio("Ya existe un cliente registrado con esa identificación.");
        }

        if (clienteDAO.buscarPorCorreo(nuevoCliente.getCorreoElectronico()) != null) {
            throw new ExcepcionDeNegocio("Ya existe un cliente registrado con ese correo electrónico.");
        }

        if (!clienteDAO.registrar(nuevoCliente)) {
            throw new ExcepcionDeNegocio("No fue posible registrar el cliente.");
        }

    }

    public ArrayList<Cliente> listarClientes() {

        return clienteDAO.listar();

    }

    public Cliente buscarCliente(String identificacion) {

        return clienteDAO.buscar(identificacion);

    }

    public void modificarCliente(Cliente cliente, String nombre, String primerApellido, String segundoApellido, String correoElectronico, String numeroTelefono) throws ExcepcionDeNegocio {

        if (cliente == null) {
            throw new ExcepcionDeNegocio("El cliente no existe.");
        }

        Cliente clientePersistido =
                clienteDAO.buscar(cliente.getIdentificacion());

        if (clientePersistido == null) {
            throw new ExcepcionDeNegocio("El cliente no existe.");
        }

        Cliente clienteCorreo =
                clienteDAO.buscarPorCorreo(correoElectronico);

        if (clienteCorreo != null &&
                !clienteCorreo.getIdentificacion().equals(clientePersistido.getIdentificacion())) {

            throw new ExcepcionDeNegocio("Ya existe un cliente registrado con ese correo electrónico.");

        }

        clientePersistido.setNombre(nombre);
        clientePersistido.setPrimerApellido(primerApellido);
        clientePersistido.setSegundoApellido(segundoApellido);
        clientePersistido.setCorreoElectronico(correoElectronico);
        clientePersistido.setNumeroTelefono(numeroTelefono);

        if (!clienteDAO.modificar(clientePersistido)) {
            throw new ExcepcionDeNegocio("No fue posible modificar el cliente.");
        }

    }

    public void eliminarCliente(Cliente cliente) throws ExcepcionDeNegocio {

        if (cliente == null) {
            throw new ExcepcionDeNegocio("El cliente no existe.");
        }

        Cliente clientePersistido =
                clienteDAO.buscar(cliente.getIdentificacion());

        if (clientePersistido == null) {
            throw new ExcepcionDeNegocio("El cliente no se encuentra registrado.");
        }

        if (!clienteDAO.eliminar(clientePersistido.getIdentificacion())) {
            throw new ExcepcionDeNegocio("No fue posible eliminar el cliente.");
        }

    }

}