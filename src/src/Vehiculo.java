public class Vehiculo {
    protected int espacio;
    protected int ID;

    public Vehiculo(int espacio, int ID) {
        this.espacio = espacio;
        this.ID = ID;
    }

    public int getEspacio() {
        return espacio;
    }

    public int getID() {
        return ID;
    }
}