import java.util.ArrayList;

public class Parking {
    private final Database database;
    private final ArrayList<Vehiculo> vehiculos;
    private final VehiculoDAO vehiculoDAO;
    private final int lenghtParking;
    private int espacioOcupado;

    public Parking( int lenghtParking) {
        this.database = Database.getInstance();
        this.vehiculoDAO = new VehiculoDAO(database);
        this.vehiculos = new ArrayList<>();
        this.lenghtParking = lenghtParking;
        this.espacioOcupado = 0;
    }

    public boolean introducirParking(Vehiculo v) {
        if (espacioOcupado + v.getEspacio() <= lenghtParking) {
            vehiculos.add(v);
            espacioOcupado += v.getEspacio();
            vehiculoDAO.insertVehiculo(v instanceof Coche ? "Coche" : "Camion", true);
            System.out.println("Vehículo introducido en el parking.");
            return true;
        } else {
            System.out.println("No hay suficiente espacio en el parking.");
            return false;
        }
    }

    public boolean sacarParking(int ID) {
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            if (v.getID() == ID) {
                vehiculos.remove(i);
                espacioOcupado -= v.getEspacio();
                int dbID = getDatabaseID(v.getID());
                if (dbID != -1) {
                    vehiculoDAO.deleteVehiculo(dbID);
                    System.out.println("Vehículo retirado del parking.");
                    return true;
                }
            }
        }
        System.out.println("Vehículo no encontrado.");
        return false;
    }

    public void comprobarParking() {
        System.out.println("Estado actual del parking:");
        vehiculoDAO.selectAllVehiculos();
        System.out.println("Espacio ocupado: " + espacioOcupado + "/" + lenghtParking);
    }

    private int getDatabaseID(int vehiculoID) {
        // Implementa este método para mapear el ID del vehículo con el ID de la base de datos
        // Este es un ejemplo sencillo; necesitas mejorarlo según tu lógica de negocio
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            if (v.getID() == vehiculoID) {
                return i + 1;
            }
        }
        return -1;
    }
}