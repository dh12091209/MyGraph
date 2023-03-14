public class Main {
    public static void main(String[] args) {
        MyGraph g = new MyGraph();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");
        g.insertVertex("G");
        g.insertVertex("H");
        g.insertVertex("I");
        g.insertVertex("J");
        g.insertVertex("K");
        g.insertVertex("L");
        g.insertVertex("M");
        g.insertVertex("N");
        g.insertVertex("O");
        g.insertVertex("P");

        g.insertEdge("A","B","1");
        g.insertEdge("C","D","2");
        g.insertEdge("A","F","3");
        g.insertEdge("B","G","4");
        g.insertEdge("D","G","5");
        g.insertEdge("D","H","6");
        g.insertEdge("E","F","7");
        g.insertEdge("G","H","8");
        g.insertEdge("E","I","9");
        g.insertEdge("E","J","10");
        g.insertEdge("G","J","11");
        g.insertEdge("G","K","12");
        g.insertEdge("H","L","13");
        g.insertEdge("I","J","14");
        g.insertEdge("K","L","15");
        g.insertEdge("I","M","16");
        g.insertEdge("J","N","17");
        g.insertEdge("K","P","18");
        g.insertEdge("L","P","19");
        g.insertEdge("M","N","20");
        g.insertEdge("O","P","21");



        g.print();
        System.out.println("Num Vertices: " + g.numVertices());
        System.out.println("Num Edges: " + g.numEdges());
        System.out.println("Edge between A and B: " + g.getEdge("A","B"));
        System.out.println("Edge between K and L: " + g.getEdge("K","L"));

        String[] ans = g.endVertices("19");
        System.out.println("Vertices connected by Edge 4: " + ans[0] + ", " + ans[1]);

        System.out.println("Vertex opposite Vertex I and Edge 14: " + g.opposite("I","14"));
        System.out.println("Vertex opposite Vertex N and Edge 20: " + g.opposite("N","20"));

        System.out.println("In degree of G: " + g.inDegree("G"));
        System.out.println("In degree of G: " + g.outDegree("G"));
        System.out.println("In degree of K: " + g.inDegree("K"));
        System.out.println("In degree of K: " + g.outDegree("K"));


        System.out.println("All vertices: " + g.vertices());
        System.out.println("All edges: " + g.edges());
        g.removeVertex("I");
//        g.removeEdge("14");
        System.out.println("All vertices: " + g.vertices());
        System.out.println("All edges: " + g.edges());
    }
}
