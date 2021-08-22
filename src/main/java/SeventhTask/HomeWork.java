package SeventhTask;

public class HomeWork {

    private static final String[] vertexName = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static final int maxVertexCount = 10;

    public static void main(String[] args) {
        breadthSearch();
    }

    private static void breadthSearch() {
        Graph graph = new GraphImpl(maxVertexCount);
        for (int i = 0; i < vertexName.length; i++) {
            graph.addVertex(vertexName[i]);
        }
        graph.addEdge("A", "B", "C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "G");
        graph.addEdge("D", "I");
        graph.addEdge("E", "F");
        graph.addEdge("G", "H");
        graph.addEdge("F","J");
        graph.addEdge("H","J");
        graph.addEdge("I","J");

        graph.shortWay("A", "J");
//        graph.display();

    }
}
