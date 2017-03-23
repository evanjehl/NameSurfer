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
		graphEntries = new ArrayList<NameSurferEntry>();
		graphColors = new HashMap<NameSurferEntry, Color>();
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
		graphEntries.add(entry);
		switch(graphEntries.size() % 4) {
			case 1:
				graphColors.put(entry, Color.BLACK);
				break;
			case 2:
				graphColors.put(entry, Color.RED);
				break;
			case 3:
				graphColors.put(entry, Color.BLUE);
				break;
			case 0:
				graphColors.put(entry, Color.MAGENTA);
				break;
		}
		update();
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
		for (int i = 0; i < graphEntries.size(); i++) {
			ArrayList<GPoint> points = new ArrayList<GPoint>();
			ArrayList<GLabel> labels = new ArrayList<GLabel>();
			ArrayList<GLine> lines = new ArrayList<GLine>();
			for (int j = 0; j < NDECADES; j++) {
				if (graphEntries.get(i).getRank(j) == 0) {
					points.add(new GPoint(getWidth() * j / 11, getHeight() - GRAPH_MARGIN_SIZE));
					labels.add(new GLabel(graphEntries.get(i).getName() + " *"));
				} else {
					points.add(new GPoint(getWidth() * j / 11, ((getHeight() - GRAPH_MARGIN_SIZE) * graphEntries.get(i).getRank(j) / MAX_RANK) + GRAPH_MARGIN_SIZE));
					labels.add(new GLabel(graphEntries.get(i).getName() + " " + graphEntries.get(i).getRank(j)));
				}
			}
			for (int j = 0; j < NDECADES; j++) {
				if (points.get(j).getY() >= points.get(j + 1).getY()) {
					labels.get(j).setLocation(points.get(j).getX() + getWidth() / 400, points.get(j).getY() - getHeight() / 400 - labels.get(j).getAscent());
				} else {
					labels.get(j).setLocation(points.get(j).getX() + getWidth() / 400, points.get(j).getY() + getHeight() / 400 + labels.get(j).getAscent());
				}
				labels.get(j).setColor(graphColors.get(graphEntries.get(i)));
			}
			for (int j = 0; j < NDECADES - 1; j++) {
				lines.add(new GLine(points.get(j).getX(), points.get(j).getY(), points.get(j + 1).getX(), points.get(j + 1).getY()));
				lines.get(j).setColor(graphColors.get(graphEntries.get(i)));
			}
			
		}
	}
	
	private ArrayList<NameSurferEntry> graphEntries;
	
	private HashMap<NameSurferEntry, Color> graphColors;
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
