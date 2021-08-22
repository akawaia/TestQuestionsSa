package SeventhTask;

import java.util.*;

public class GraphImpl implements Graph {
    private final List<Vertex> vertexList;
    private final boolean[][] adjMatrix;
    private static int countLength = 0;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new boolean[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, String... others) {
        boolean result = addEdge(startLabel, secondLabel);
        for (String other : others) {
            result &= addEdge(startLabel, other);
        }
        return result;
    }

    public boolean addEdge(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = true;
//        adjMatrix[endIndex][startIndex] = true;

        return true;
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверный индекс: " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        System.out.println(vertex.getLabel());
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        vertex.setLength(countLength);
//        System.out.println(vertex.getLabel() + " " + vertex.getLength());
        Vertex parent = queue.peek();

        queue.add(vertex);
        if (parent != null) vertex.setParent(parent);

        vertex.setVisited(true);
        countLength++;
    }

    @Override
    public void shortWay(String startLabel, String findLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверный индекс: " + startLabel);
        }
        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
                countLength--;
            } else {
                countLength = queue.peek().getLength() + 1;
                queue.remove();
            }
        }
        getShortWay(findLabel);
    }

    @Override
    public void getShortWay(String findLabel) {
        String currentLabel;
        List<Vertex> result = new ArrayList<>();
        for (int i = 0; i < vertexList.size(); i++) {
            currentLabel = vertexList.get(i).getLabel();
            if (currentLabel.equals(findLabel)) {
                result.add(vertexList.get(i));
                Vertex parent = vertexList.get(i).getParent();

                while (parent != null) {
                    result.add(parent);
                    parent = parent.getParent();

                }
            }
        }
        System.out.println(result);
    }
}
