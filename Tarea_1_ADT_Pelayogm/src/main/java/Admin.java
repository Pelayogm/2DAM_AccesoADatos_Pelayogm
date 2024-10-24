public class Admin extends Usuario {
    private int id;
    private
    private boolean sesionAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSesionAdmin() {
        return sesionAdmin;
    }

    public void setSesionAdmin(boolean sesionAdmin) {
        this.sesionAdmin = sesionAdmin;
    }

    public Admin(int id, boolean sesionAdmin) {
        this.id = id;
        this.sesionAdmin = sesionAdmin;
    }
}
