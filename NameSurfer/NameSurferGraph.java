/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		graphEntries = new HashMap<String, NameSurferEntry>();
		colorCounter = 0;
		update();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		graphEntries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		graphEntries.put(entry.getName(), entry);
		switch(colorCounter % 4) {
			case 0:
				entry.setColor(Color.BLACK);
				break;
			case 1:
				entry.setColor(Color.RED);
				break;
			case 2:
				entry.setColor(Color.BLUE);
				break;
			case 3:
				entry.setColor(Color.MAGENTA);
				break;
		}
		colorCounter++;
		update();
	}
	
	public void remove(String name) {
		if (graphEntries.containsKey(name)) {
			graphEntries.remove(name);
			update();
		}
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		for (int i = 0; i < NDECADES; i++) {
			GLine line = new GLine((getWidth() * i) / 11, 0, (getWidth() * i) / 11, getHeight());
			GLabel decade = new GLabel("" + (1900 + 10 * i), (getWidth() * i) / 11 + 2, getHeight() - 2);
			add(line);
			add(decade);
		}
		GLine upperMargin = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine lowerMargin = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(upperMargin);
		add(lowerMargin);
		drawLines();
	}
	
	private void drawLines() {
		points = new ArrayList<GPoint>();
		labels = new ArrayList<GLabel>();
		lines = new ArrayList<GLine>();
		for (String name: graphEntries.keySet()) {
			for (int j = 0; j < NDECADES; j++) {
				if (graphEntries.get(name).getRank(j) == 0) {
					points.add(new GPoint(getWidth() * j / 11, getHeight() - GRAPH_MARGIN_SIZE));
					labels.add(new GLabel(graphEntries.get(name).getName() + " *"));
				} else {
					points.add(new GPoint(getWidth() * j / 11, ((getHeight() - GRAPH_MARGIN_SIZE) * graphEntries.get(name).getRank(j) / MAX_RANK) + GRAPH_MARGIN_SIZE));
					labels.add(new GLabel(graphEntries.get(name).getName() + " " + graphEntries.get(name).getRank(j)));
				}
			}
			for (int j = 0; j < NDECADES; j++) {
				if (j < NDECADES - 1) {
					if (points.get(j).getY() >= points.get(j + 1).getY() && points.get(j).getY() < (getHeight() - GRAPH_MARGIN_SIZE)) {
						labels.get(j).setLocation(points.get(j).getX() + getWidth() / 400, points.get(j).getY() + getHeight() / 400 + labels.get(j).getAscent());
					} else {
						labels.get(j).setLocation(points.get(j).getX() + getWidth() / 400, points.get(j).getY() - getHeight() / 400 - labels.get(j).getDescent());
					}
				} else {
					labels.get(j).setLocation(points.get(j).getX() + getWidth() / 400, points.get(j).getY() - getHeight() / 400 - labels.get(j).getDescent());
				}
				labels.get(j).setColor(graphEntries.get(name).getColor());
				add(labels.get(j));
			}
			for (int j = 0; j < NDECADES - 1; j++) {
				lines.add(new GLine(points.get(j).getX(), points.get(j).getY(), points.get(j + 1).getX(), points.get(j + 1).getY()));
				lines.get(j).setColor(graphEntries.get(name).getColor());
				add(lines.get(j));
			}
			points.clear();
			labels.clear();
			lines.clear();
		}
	}
	
	private HashMap<String, NameSurferEntry> graphEntries;
	
	private ArrayList<GPoint> points;
	
	private ArrayList<GLabel> labels;
	
	private ArrayList<GLine> lines;
	
	private int colorCounter;
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
