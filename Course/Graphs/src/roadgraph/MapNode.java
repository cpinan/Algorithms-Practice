package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {

	private GeographicPoint point;
	private List<MapEdge> edges;
	private double distance;

	public MapNode(GeographicPoint point) {
		this.point = point;
		this.edges = new ArrayList<>();
		this.distance = Double.POSITIVE_INFINITY;
	}

	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName, String roadType, double length) {
		MapEdge edge = new MapEdge(from, to, roadName, roadType, length);
		edges.add(edge);
	}

	public GeographicPoint getPoint() {
		return point;
	}

	public List<MapEdge> getEdges() {
		return edges;
	}

	public int getNumEdges() {
		return getEdges().size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(point);
		builder.append("\n");
		builder.append("\n");
		for (MapEdge edge : edges) {
			builder.append("\t");
			builder.append(edge);
			builder.append("\n");
			builder.append("\n");
		}
		return builder.toString();
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void addDistance(double distance) {
		this.distance += distance;
	}

}
