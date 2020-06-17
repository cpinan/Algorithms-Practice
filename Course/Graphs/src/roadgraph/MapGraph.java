/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 *         A class which represents a graph of geographic locations Nodes in the
 *         graph are intersections between
 *
 */
public class MapGraph {

	private List<MapNode> nodes;
	private Map<GeographicPoint, MapNode> map;

	/**
	 * Create a new empty MapGraph
	 */
	public MapGraph() {
		this.nodes = new ArrayList<>();
		this.map = new HashMap<>();
	}

	/**
	 * Get the number of vertices (road intersections) in the graph
	 * 
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		return nodes.size();
	}

	/**
	 * Return the intersections, which are the vertices in this graph.
	 * 
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices() {
		Set<GeographicPoint> verticesSet = new HashSet<>();
		for (MapNode node : nodes) {
			verticesSet.add(node.getPoint());
		}
		return verticesSet;
	}

	/**
	 * Get the number of road segments in the graph
	 * 
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges() {
		int edges = 0;
		for (MapNode node : nodes) {
			edges += node.getNumEdges();
		}
		return edges;
	}

	/**
	 * Add a node corresponding to an intersection at a Geographic Point If the
	 * location is already in the graph or null, this method does not change the
	 * graph.
	 * 
	 * @param location
	 *            The location of the intersection
	 * @return true if a node was added, false if it was not (the node was already
	 *         in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location) {
		boolean added = false;
		if (!map.containsKey(location)) {
			MapNode node = new MapNode(location);
			nodes.add(node);
			map.put(location, node);
			added = true;
		}
		return added;
	}

	/**
	 * Adds a directed edge to the graph from pt1 to pt2. Precondition: Both
	 * GeographicPoints have already been added to the graph
	 * 
	 * @param from
	 *            The starting point of the edge
	 * @param to
	 *            The ending point of the edge
	 * @param roadName
	 *            The name of the road
	 * @param roadType
	 *            The type of the road
	 * @param length
	 *            The length of the road, in km
	 * @throws IllegalArgumentException
	 *             If the points have not already been added as nodes to the graph,
	 *             if any of the arguments is null, or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName, String roadType, double length)
			throws IllegalArgumentException {
		if (map.containsKey(from) && map.containsKey(to)) {
			map.get(from).addEdge(from, to, roadName, roadType, length);
		}
	}

	/**
	 * Find the path from start to goal using breadth first search
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @return The list of intersections that form the shortest (unweighted) path
	 *         from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {
		};
		return bfs(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using breadth first search
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @param nodeSearched
	 *            A hook for visualization. See assignment instructions for how to
	 *            use it.
	 * @return The list of intersections that form the shortest (unweighted) path
	 *         from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched) {
		Map<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();

		Queue<GeographicPoint> bfsQueue = new LinkedList<>();
		Set<GeographicPoint> visited = new HashSet<>();
		bfsQueue.add(start);
		visited.add(start);
		while (!bfsQueue.isEmpty()) {
			GeographicPoint current = bfsQueue.remove();
			nodeSearched.accept(current);
			if (current == goal) {
				break;
			}
			if (map.containsKey(current)) {
				for (MapEdge edge : map.get(current).getEdges()) {
					final GeographicPoint to = edge.getTo();
					if (!visited.contains(to)) {
						visited.add(to);
						bfsQueue.add(to);
						parentMap.put(to, current);
					}
				}
			}
		}
		LinkedList<GeographicPoint> path = constructPath(start, goal, parentMap);
		return path.isEmpty() ? null : path;
	}

	/**
	 * Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
		Consumer<GeographicPoint> temp = (x) -> {
		};
		return dijkstra(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @param nodeSearched
	 *            A hook for visualization. See assignment instructions for how to
	 *            use it.
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched) {
		Set<MapNode> visited = new HashSet<>();
		for (MapNode node : nodes) {
			node.setDistance(Double.POSITIVE_INFINITY);
		}
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>(new Comparator<MapNode>() {

			@Override
			public int compare(MapNode o1, MapNode o2) {
				if (o1.getDistance() < o2.getDistance()) {
					return -1;
				} else if (o1.getDistance() > o2.getDistance()) {
					return 1;
				}
				return 0;
			}
		});

		map.get(start).setDistance(0);
		queue.add(map.get(start));
		Map<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();

		int visitedNodes = 0;
		while (!queue.isEmpty()) {
			final MapNode mn = queue.remove();
			visitedNodes++;
			if (mn.getPoint().equals(goal))
				break;
			nodeSearched.accept(mn.getPoint());
			if (!visited.contains(mn)) {
				//visitedNodes++;
				visited.add(mn);
				List<MapEdge> edges = mn.getEdges();
				for (MapEdge edge : edges) {
					double newDistance = mn.getDistance() + edge.length();
					GeographicPoint to = edge.getTo();
					MapNode toNode = map.get(to);
					if (newDistance < toNode.getDistance()) {
						toNode.setDistance(newDistance);
						queue.add(map.get(to));
						parentMap.put(to, mn.getPoint());
					}
				}
			}
		}
		System.out.println("Dijsktra = " + visitedNodes);
		LinkedList<GeographicPoint> path = constructPath(start, goal, parentMap);
		return path.isEmpty() ? null : path;
	}

	/**
	 * Find the path from start to goal using A-Star search
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {
		};
		return aStarSearch(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using A-Star search
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @param nodeSearched
	 *            A hook for visualization. See assignment instructions for how to
	 *            use it.
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, final GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched) {
		Set<MapNode> visited = new HashSet<>();
		for (MapNode node : nodes) {
			node.setDistance(Double.POSITIVE_INFINITY);
		}
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>(new Comparator<MapNode>() {

			@Override
			public int compare(MapNode o1, MapNode o2) {
				double o1ToGoal = o1.getPoint().distance(goal) + o1.getDistance();
				double o2ToGoal = o2.getPoint().distance(goal) + o2.getDistance();
				if (o1ToGoal < o2ToGoal) {
					return -1;
				} else if (o1ToGoal > o2ToGoal) {
					return 1;
				}
				return 0;
			}
		});

		map.get(start).setDistance(0);
		queue.add(map.get(start));
		Map<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();

		int visitedNodes = 0;
		while (!queue.isEmpty()) {
			final MapNode mn = queue.remove();
			visitedNodes++;
			if (mn.getPoint().equals(goal))
				break;
			nodeSearched.accept(mn.getPoint());
			if (!visited.contains(mn)) {
				visited.add(mn);
				List<MapEdge> edges = mn.getEdges();
				for (MapEdge edge : edges) {
					double newDistance = mn.getDistance() + edge.length();
					GeographicPoint to = edge.getTo();
					MapNode toNode = map.get(to);
					if (newDistance < toNode.getDistance()) {
						toNode.setDistance(newDistance);
						queue.add(map.get(to));
						parentMap.put(to, mn.getPoint());
					}
				}
			}
		}
		System.out.println("aStar = " + visitedNodes);
		LinkedList<GeographicPoint> path = constructPath(start, goal, parentMap);
		return path.isEmpty() ? null : path;
	}

	private LinkedList<GeographicPoint> constructPath(GeographicPoint start, GeographicPoint goal,
			Map<GeographicPoint, GeographicPoint> parentMap) {
		LinkedList<GeographicPoint> path = new LinkedList<>();
		if (parentMap.containsKey(goal)) {
			GeographicPoint current = goal;
			while (current != null && !current.equals(start)) {
				path.addFirst(current);
				current = parentMap.get(current);
			}
			path.addFirst(start);
		}
		return path;
	}

	public static void main(String[] args) {
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");

		// You can use this method for testing.

		/*
		 * Here are some test cases you should try before you attempt the Week 3 End of
		 * Week Quiz, EVEN IF you score 100% on the programming assignment.
		 */

		// MapGraph simpleTestMap = new MapGraph();
		// GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		//
		// GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		// GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);

		// System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar
		// should be 5");
		// List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart, testEnd);
		// List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,
		// testEnd);
		//
		// MapGraph testMap = new MapGraph();
		// GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		//
		// // A very simple test using real data
		// testStart = new GeographicPoint(32.869423, -117.220917);
		// testEnd = new GeographicPoint(32.869255, -117.216927);
		// System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should
		// be 5");
		// testroute = testMap.dijkstra(testStart, testEnd);
		// testroute2 = testMap.aStarSearch(testStart, testEnd);
		//
		// // A slightly more complex test using real data
		// testStart = new GeographicPoint(32.8674388, -117.2190213);
		// testEnd = new GeographicPoint(32.8697828, -117.2244506);
		// System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should
		// be 10");
		// testroute = testMap.dijkstra(testStart, testEnd);
		// testroute2 = testMap.aStarSearch(testStart, testEnd);

		/* Use this code in Week 3 End of Week Quiz */

		// MapGraph theMap = new MapGraph();
		// System.out.print("DONE. \nLoading the map...");
		// GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		// System.out.println("DONE.");
		//
		// GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		// GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		//
		// List<GeographicPoint> route = theMap.dijkstra(start, end);
		// List<GeographicPoint> route2 = theMap.aStarSearch(start, end);

		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);

		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);

		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart, testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart, testEnd);
		System.out.println();

		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);

		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart, testEnd);
		testroute2 = testMap.aStarSearch(testStart, testEnd);
		System.out.println();

		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart, testEnd);
		testroute2 = testMap.aStarSearch(testStart, testEnd);
		System.out.println();

		//

		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);

		System.out.println("Test 4");
		List<GeographicPoint> route = theMap.dijkstra(start, end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start, end);

		MapGraph theMap1 = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap1);
		System.out.println("DONE.");

		GeographicPoint start1 = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end1 = new GeographicPoint(32.8660691, -117.217393);

		List<GeographicPoint> route11 = theMap.dijkstra(start, end);
		List<GeographicPoint> route12 = theMap.aStarSearch(start, end);

	}

}
