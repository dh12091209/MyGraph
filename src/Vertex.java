public class Vertex extends GraphObject{
    private String name;
    private int weight;

    public Vertex(String name) {
        super(name);
    }

    public Vertex(String name, int weight) {
        super(name,weight);
    }

    public boolean equals(Vertex other){
        return this.getName().equals(other.getName());
    }
}
