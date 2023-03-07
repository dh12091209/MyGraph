import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MyGraph {
    private int numVertices;
    private int numEdges;
    private HashMap<Vertex,ArrayList<GraphPairing>> graph;

    public MyGraph(){
        numEdges = 0;
        numVertices = 0;
        graph = new HashMap<>();
    }

    public int numVertices(){
        return numVertices;
    }

    public int numEdges(){
        return numEdges;
    }

    public void insertVertex(String name){
        Vertex temp = new Vertex(name);
        //assume, for now, the given name does NOT exist in the map
        graph.put(temp,null);
        numVertices++;
    }

    public void print(){
        Set<Vertex> vertexList = graph.keySet();

        for(Vertex v: vertexList){
            System.out.print(v+": ");
            System.out.println(graph.get(v));
        }


    }
    //we need to change Vertex v1, v3 to String v1, v2
    //then we need to get all the keys, loop and find the correct
    //key with those names
    //This is O(2n), can we make this O(1)?
    public void insertEdge(String v1,String v2, String edgeName){
        Set<Vertex> vertices = graph.keySet();
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        Edge edge = new Edge(edgeName);
        for(Vertex v: vertices){
            if(v.compareTo(new Vertex(v1))==0) {
                vertex1 = v;
            }
            if(v.compareTo(new Vertex(v2))==0) {
                vertex2 = v;
            }
        }
//        Vertex vertex1 = new Vertex(v1);
//        Vertex vertex2 = new Vertex(v2);
//        Edge edge = new Edge(edgeName);


//        System.out.println("Contains v1: " + graph.containsKey(vertex1));
//        System.out.println("Contains v2: " + graph.containsKey(vertex2));
        ArrayList<GraphPairing> v1EdgeList = graph.get(vertex1);
        if(v1EdgeList == null){
            graph.put(vertex1,new ArrayList<>());
        }
        graph.get(vertex1).add(new GraphPairing(vertex2,edge));

        ArrayList<GraphPairing> v2EdgeList = graph.get(vertex2);
        if(v2EdgeList == null){
            graph.put(vertex2,new ArrayList<>());
        }
        graph.get(vertex2).add(new GraphPairing(vertex1,edge));

        numEdges++;


//        ArrayList<GraphPairing> edgeV1List = graph.getOrDefault(vertex1,new ArrayList<>());
//        edgeV1List.add(new GraphPairing(vertex2,edge));
//        graph.put(vertex1,edgeV1List);
//
//        System.out.println("Contains v2: " + graph.containsKey(vertex2));
//        ArrayList<GraphPairing> edgeV2List = graph.getOrDefault(vertex2,new ArrayList<>());
//        edgeV1List.add(new GraphPairing(vertex1,edge));
//        graph.put(vertex2,edgeV2List);
    }

    public Set<Vertex> vertices(){
        return graph.keySet();
    }




}
