package roadgraph;

import geography.GeographicPoint;

public class MapEdge {

	private GeographicPoint from;
	private GeographicPoint to;
	private String roadName;
	private String roadType;
	private double distance;
	private double length;

	public MapEdge(GeographicPoint from, GeographicPoint to, String roadName, String roadType, double length) {
		this.from = from;
		this.to = to;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
		this.distance = from.distance(to); // Just to compare with length.
	}

	public GeographicPoint getTo() {
		return to;
	}

	public double length() {
		return length;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("From: ");
		builder.append(from);
		builder.append("\n\t");

		builder.append("To: ");
		builder.append(to);
		builder.append("\n\t");

		builder.append("Road Name: ");
		builder.append(roadName);
		builder.append("\n\t");

		builder.append("Road Type: ");
		builder.append(roadType);
		builder.append("\n\t");

		builder.append("Distance: ");
		builder.append(distance);
		builder.append("\n\t");

		builder.append("Length: ");
		builder.append(length);

		return builder.toString();
	}
}
