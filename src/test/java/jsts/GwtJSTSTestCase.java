/*
 * Copyright (c) 2001-2016 Territorium Online Srl. All Rights Reserved.
 * 
 * This file contains Original Code and/or Modifications of Original Code as defined in and that are subject to the
 * Territorium Online License Version 1.0. You may not use this file except in compliance with the License. Please
 * obtain a copy of the License at http://www.tol.info/license/ and read it before using this file.
 * 
 * The Original Code and all software distributed under the License are distributed on an 'AS IS' basis, WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, AND TERRITORIUM ONLINE HEREBY DISCLAIMS ALL SUCH WARRANTIES,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, QUIET ENJOYMENT OR
 * NON-INFRINGEMENT. Please see the License for the specific language governing rights and limitations under the
 * License.
 */
package jsts;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;

import jsts.geom.Coordinate;
import jsts.geom.CoordinateList;
import jsts.geom.CoordinateSequence;
import jsts.geom.CoordinateSequenceFactory;
import jsts.geom.GeometryFactory;
import jsts.geom.LinearRing;
import jsts.geom.PrecisionModel;
import tol.j2cl.elem.global.Array;

/**
 *
 * <p>
 * The <code>GwtJSTSTestCase</code> class
 * </p>
 * <p>
 * Copyright: 2003 - 2016 <a href="http://www.teritoriumonline.com">Territorium Online Srl.</a>
 * </p>
 * <p>
 * Via Buozzi 12, 39100 Bolzano, Italy.
 * </p>
 * <p>
 * </p>
 * @author <a href="mailto:mapaccel@teritoriumonline.com">Peter Zanetti</a>.
 * @version 1.0.0,24.11.2016
 * @since 1.0.0
 */
public class GwtJSTSTestCase extends GWTTestCase {

	public static final String	MULTIPOLYGON_A	= "MULTIPOLYGON_A (((260 10, 260 80, 810 80, 810 10, 260 10)), ((260 100, 260 420, 810 420, 810 100, 260 100)))";
	public static final String	POLYGON_A				= "POLYGON ((260 250, 810 250, 810 50, 260 50, 260 250))";
	public static final String	POLYGON_B				= "POLYGON ((390 160, 390 400, 680 400, 680 160, 390 160))";
	public static final String	POLYGON_C				= "POLYGON ((460 90, 460 190, 580 190, 580 90, 460 90))";
	public static final String	POLYGON_D				= "POLYGON ((230 260, 230 400, 370 400, 370 260, 230 260))";

	private static final String	MODUL_NAME			= "jsts.GwtJSTSTest";

	@Override
	public String getModuleName() {
		return GwtJSTSTestCase.MODUL_NAME;
	}

	private static native String getNodeName(Element elem) /*-{
		return (elem.nodeName || "").toLowerCase();
	}-*/;

	/**
	 * Removes all elements in the body, except scripts and iframes.
	 */
	@Override
	public void gwtSetUp() throws Exception {
		final Element bodyElem = RootPanel.getBodyElement();

		final List<Element> toRemove = new ArrayList<Element>();
		for (int i = 0, n = DOM.getChildCount(bodyElem); i < n; ++i) {
			final Element elem = DOM.getChild(bodyElem, i);
			final String nodeName = getNodeName(elem);
			if (!"script".equals(nodeName) && !"iframe".equals(nodeName)) {
				toRemove.add(elem);
			}
		}

		for (int i = 0, n = toRemove.size(); i < n; ++i) {
			DOM.removeChild(bodyElem, toRemove.get(i));
		}
		inject();
	}

	private final void inject() {
		// Start loading the library.
		ScriptInjector.fromString(JsResLoader.getJsRes().jsts().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		// ScriptInjector.fromString(JsResLoader.getJsRes().jstsMin().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		ScriptInjector.fromString(JsResLoader.getJsRes().ol3().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
	}

	protected GeometryFactory createGeometryFactory() {
		return new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING));
	}

	protected GeometryFactory createGeometryFactory(int SRID) {
		return new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), SRID);
	}

	protected final Coordinate[] createCoordinates() {
		return new Coordinate[] {
				new Coordinate(260, 250), new Coordinate(810, 250), new Coordinate(810, 50), new Coordinate(260, 50),
				new Coordinate(260, 250) };
	}

	protected final Array<Coordinate> createCoordinateArray() {
		return Array.of(createCoordinates());
	}

	protected final ArrayList<Coordinate> createCoordinateArrayList() {
		final ArrayList<Coordinate> coords = new ArrayList<Coordinate>(3);
		coords.add(new Coordinate(260, 250));
		coords.add(new Coordinate(810, 250));
		coords.add(new Coordinate(810, 50));
		return coords;
	}

	protected final CoordinateList createCoordinateList() {
		final CoordinateList coordList = new CoordinateList();
		for (final Coordinate coord : createCoordinates()) {
			coordList.add(coord);
		}
		return coordList;
	}

	protected Coordinate[] createPolygonCoordinates() {
		return new Coordinate[] {
				new Coordinate(260, 250), new Coordinate(810, 250), new Coordinate(810, 50), new Coordinate(260, 50),
				new Coordinate(260, 250) };
	}

	protected LinearRing createLinearRing() {
		final GeometryFactory geometryFactory = createGeometryFactory();
		final Coordinate[] coords = new Coordinate[] {
				new Coordinate(260, 250), new Coordinate(810, 250), new Coordinate(810, 50), new Coordinate(260, 50),
				new Coordinate(260, 250) };
		final CoordinateSequenceFactory coordSeqFactory = geometryFactory.getCoordinateSequenceFactory();
		final CoordinateSequence coordSeq = coordSeqFactory.create(coords);
		final LinearRing linearRing = new LinearRing(coordSeq, geometryFactory);
		return linearRing;
	}
}
