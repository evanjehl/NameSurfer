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
		update();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		graphEntries.add(entry);
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
			for (int j = 0; j < NDECADES - 1; j ++) {
				GPoint p1;
				GPoint p2;
				if (graphEntries.get(i).getRank(j) == 0) {
					p1 = new GPoint(getWidth() * j / 11, getHeight() - GRAPH_MARGIN_SIZE);
				} else {
					p1 = new GPoint(getWidth() * j / 11, ((getHeight() - GRAPH_MARGIN_SIZE) * graphEntries.get(i).getRank(j + 1) / MAX_RANK) + GRAPH_MARGIN_SIZE); 
				}
				if (graphEntries.get(i).getRank(j + 1) == 0) {
					p2 = new GPoint(getWidth() * (j + 1) / 11, getHeight() - GRAPH_MARGIN_SIZE);
				} else {
					p2 = new GPoint(getWidth() * (j + 1) / 11, ((getHeight() - GRAPH_MARGIN_SIZE) * graphEntries.get(i).getRank(j + 1) / MAX_RANK) + GRAPH_MARGIN_SIZE); 
				}
				GLine line = new GLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
				add(line);
				switch(i % 4) {
					
				}
			}
		}
	}
	
	private ArrayList<NameSurferEntry> graphEntries;
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
