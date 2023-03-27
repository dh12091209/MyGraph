import com.sun.source.tree.NewArrayTree;

import java.lang.reflect.Array;
import java.util.*;

public class MyGraph {
    private int numVertices;
    private int numEdges;
    private HashMap<Vertex,ArrayList<GraphPairing>> graph;
    static Stack<GraphPairing> stack = new Stack<GraphPairing>();

    // Andy added
    //private boolean[] visited;
    private HashMap<Vertex,Boolean> visited; 

    public MyGraph(){
        numEdges = 0;
        numVertices = 0;
        graph = new HashMap<>();
        // Andy added
        visited = new HashMap<>();
    }

    public int numVertices(){
        return numVertices;
    }

    public int numEdges(){
        return numEdges;
    }

    // Andy added
    public void initVisited() {
        Set<Vertex> vertexList = graph.keySet();
        for(Vertex v: vertexList){
            visited.put(v,false);
        }
    }

    public void insertVertex(String name){
        Vertex temp = new Vertex(name);
        //assume, for now, the given name does NOT exist in the map
        graph.put(temp,null);
        // Andy added
        visited.put(temp,false);
        numVertices++;
    }

    public void print(){
        Set<Vertex> vertexList = graph.keySet();

        for(Vertex v: vertexList){
            System.out.print(v+": ");
            System.out.println(graph.get(v));
        }
    }
    public String[] endVertices(String edge){
        String[] vertices = new String[2];
        Edge e = getEdgeFromString(edge);
        Vertex v1;
        Vertex v2;

        Set<Vertex> vertexList = graph.keySet();
        for(Vertex v: vertexList){
            ArrayList<GraphPairing> gps = graph.get(v);
            for(GraphPairing gp : gps){
                if(gp.getEdge() == e){
                    v1 = v;
                    v2 = gp.getVertex();
                    vertices[0] = v1.getName();
                    vertices[1] = v2.getName();
                    return vertices;
                }
            }
        }
        return null;
    }
    public String getEdge(String vertex1, String vertex2){
        Vertex v1 = getVertexFromString(vertex1);
        Vertex v2 = getVertexFromString(vertex2);

        ArrayList<GraphPairing> gps = graph.get(v1);
        for(GraphPairing gp: gps){
            if (gp.getVertex() == v2){
                return gp.getEdge().getName();
            }
        }
        return null;
    }
    public String opposite(String vertex, String edge){
        Vertex v= getVertexFromString(vertex);
        Edge e = getEdgeFromString(edge);

        ArrayList<GraphPairing> gps = graph.get(v);
        for(GraphPairing gp: gps){
            if(gp.getEdge() == e) return gp.getVertex().getName();
        }
        return null;
    }
    private Vertex getVertexFromString (String s){
        Set<Vertex> vertices = graph.keySet();
        for(Vertex v:vertices){
            if(v.getName().equals(s)) return v;
        }
        return null;
    }
    public int outDegree(String vertex){
        Vertex v = getVertexFromString(vertex);
        ArrayList<GraphPairing> gps = graph.get(v);
        if(gps == null) return 0;
        return gps.size();
    }
    public int inDegree(String vertex){
        Vertex v = getVertexFromString(vertex);
        ArrayList<GraphPairing> gps = graph.get(v);
        if(gps == null) return 0;
        return gps.size();
    }

    public Set<String> vertices(){
        Set<String> allVertices = new HashSet<>();
        Set<Vertex> vertexSet = graph.keySet();

        for(Vertex v: vertexSet){
            allVertices.add(v.getName());
        }

        return allVertices;

    }

    public Set<String> edges(){
        Set<String> allEdges = new HashSet<>();
        Set<Vertex> vertices = graph.keySet();

        for(Vertex v: vertices){
            ArrayList<GraphPairing> gps = graph.get(v);
            if(gps!=null){
                for(GraphPairing gp: gps){
                    allEdges.add(gp.getEdge().getName());
                }
            }
        }
        return allEdges;
    }
    private Edge getEdgeFromString(String s){
        Set<Vertex> vertices = graph.keySet();
        for(Vertex v:vertices){
            ArrayList<GraphPairing> gps = graph.get(v);
            if(gps!=null){
                for(GraphPairing g : gps){
                    Edge e = g.getEdge();
                    if(e.getName().equals(s)) return e;
                }
        }

    }
        return null;
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
    public void removeVertex(String vertex){
        Set<Vertex> vertices = graph.keySet();
        Vertex ver = getVertexFromString(vertex);

        for(Vertex v: vertices){
            ArrayList<GraphPairing> gps = graph.get(v);
            if(gps!=null){
                for(GraphPairing gp: gps){
                    if(gp.getVertex().equals(ver)){
                        graph.get(v).remove(gp);
                    }
                }
            }
        }
        graph.remove(ver);

    }
    public void removeEdge(String edge){
        Edge e = getEdgeFromString(edge);
        Set<Vertex> vertices = graph.keySet();

        for(Vertex v: vertices){
            ArrayList<GraphPairing> gps = graph.get(v);
            if(gps!=null){
                for(GraphPairing gp: gps){
                    if(gp.getEdge().equals(e)){
                        graph.get(v).remove(gp);
                    }
                }
            }
        }

//        Set<Vertex> vertices = graph.keySet();
//        Vertex vertex1 = null;
//        Vertex vertex2 = null;
//        Edge edge = new Edge(edgeName);
//        for(Vertex v: vertices){
//            if(v.compareTo(new Vertex(v1))==0) {
//                vertex1 = v;
//            }
//            if(v.compareTo(new Vertex(v2))==0) {
//                vertex2 = v;
//            }
//        }
//        ArrayList<GraphPairing> v1EdgeList = graph.get(vertex1);
//        if(v1EdgeList == null){
//            graph.put(vertex1,new ArrayList<>());
//        }
//        graph.get(vertex1).add(new GraphPairing(vertex2,edge));
//
//        ArrayList<GraphPairing> v2EdgeList = graph.get(vertex2);
//        if(v2EdgeList == null){
//            graph.put(vertex2,new ArrayList<>());
//        }
//        graph.get(vertex2).add(new GraphPairing(vertex1,edge));
//
//        numEdges++;
    }
    public void depthFirstSearch(String startVertex){
        Set<Vertex> vertices = graph.keySet();
        Vertex ver = getVertexFromString(startVertex);

    }

    //modified
    public String findPath(String startVertex, String endVertex){
        ArrayList<GraphPairing> path = new ArrayList<GraphPairing>();
        findpathDetail(startVertex,endVertex,path);
        String returnPath = "";
        returnPath += startVertex + " -> ";
        for(GraphPairing gp:path){
            returnPath += gp.getEdge().getName() + " -> " + gp.getVertex().getName() + " -> ";
        }
        
        return returnPath;
    }
    private Boolean findpathDetail(String startVertex, String endVertex, ArrayList<GraphPairing> path){
        Vertex startVer = getVertexFromString(startVertex);
        Vertex endVer = getVertexFromString(endVertex);

        // System.out.println("Start vertex: " + startVer.getName());
        // System.out.println("End vertex: " + endVer.getName());
        visited.put(startVer, true);
        ArrayList<GraphPairing> v1EdgeList = graph.get(startVer);

        if(v1EdgeList == null){
            return false;
        }

        for(GraphPairing gp: v1EdgeList){
            Vertex v = gp.getVertex();
            if(v.equals(endVer)){
                path.add(gp);
                return true;
            }
            if(!visited.getOrDefault(v,false)){
                path.add(gp);
                if(findpathDetail(v.getName(),endVertex,path)){
                    return true;
                }
                path.remove(gp);
            }
        }

        return false;
    }





}
