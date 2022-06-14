package classesandenums;

public class Location {
    private Double x;
    private double y;
    private Long z;
    private String name;

    public Location(Double x, double y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Координата X:" + x + " Координата Y:" + y + " Координата Z:" + z + " Город:" + name;
    }

    public int hashCode() {
        return x.hashCode() + (int) y + z.hashCode() + name.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Location) {
            Location locationObj = (Location) obj;
            return x.equals(locationObj.getX()) && (y == locationObj.getY()) && z.equals(locationObj.getZ()) && name.equals(locationObj.getName());
        }
        return false;
    }
}
