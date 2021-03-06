/*
 * Copyright (c) 2001-2016 Territorium Online Srl. All Rights Reserved.
 *
 * This file contains Original Code and/or Modifications of Original Code as
 * defined in and that are subject to the Territorium Online License Version
 * 1.0. You may not use this file except in compliance with the License. Please
 * obtain a copy of the License at http://www.tol.info/license/ and read it
 * before using this file.
 *
 * The Original Code and all software distributed under the License are
 * distributed on an 'AS IS' basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS
 * OR IMPLIED, AND TERRITORIUM ONLINE HEREBY DISCLAIMS ALL SUCH WARRANTIES,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, QUIET ENJOYMENT OR NON-INFRINGEMENT. Please see the
 * License for the specific language governing rights and limitations under the
 * License.
 */
package jsts.geom;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import tol.j2cl.elem.global.Array;

/**
 * The internal representation of a list of coordinates inside a Geometry. This
 * allows Geometries to store their points using something other than the JTS
 * Coordinate class. For example, a storage-efficient implementation might store
 * coordinate sequences as an array of x's and an array of y's. Or a custom
 * coordinate class might support extra attributes like M-values.
 * 
 * Implementing a custom coordinate storage structure requires implementing the
 * CoordinateSequence and CoordinateSequenceFactory interfaces. To use the
 * custom CoordinateSequence, create a new GeometryFactory parameterized by the
 * CoordinateSequenceFactory The GeometryFactory can then be used to create new
 * Geometrys. The new Geometries will use the custom CoordinateSequence
 * implementation.
 * 
 * For an example, see the code for ExtendedCoordinateExample.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class CoordinateArraySequence extends CoordinateSequence {

	@JsConstructor
	public CoordinateArraySequence(int size) {}

	@JsConstructor
	public CoordinateArraySequence(int size, int dimension) {}

	@JsConstructor
	public CoordinateArraySequence(Array<Coordinate> coords) {}

	@JsConstructor
	public CoordinateArraySequence(CoordinateSequence coordSeq) {}
}
