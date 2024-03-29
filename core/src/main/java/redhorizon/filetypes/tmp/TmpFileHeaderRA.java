/*
 * Copyright 2012, Emanuel Rabina (http://www.ultraq.net.nz/)
 * Modifications by Mike DuVall
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package redhorizon.filetypes.tmp;

import java.nio.ByteBuffer;

/**
 * Representation of the header used in Red Alert's map tile files.
 * 
 * @author Emanuel Rabina
 */
public class TmpFileHeaderRA {

	static final int HEADER_SIZE = 40;

	final short width;
	final short height;
	final short numtiles;
	final short unknown1;
	final short tilewidth;
	final short tileheight;
	final int filesize;
	final int imagedata;
	final int unknown2;
	final int unknown3;
	final int index2;
	final int unknown4;
	final int index1;

	/**
	 * Constructor, generates header data from the given <tt>ByteBuffer</tt>.
	 * 
	 * @param bytes <tt>ByteBuffer</tt> containing RA template header data.
	 */
	TmpFileHeaderRA(ByteBuffer bytes) {

		width      = bytes.getShort();
		height     = bytes.getShort();
		numtiles   = bytes.getShort();
		unknown1   = bytes.getShort();
		tilewidth  = bytes.getShort();
		tileheight = bytes.getShort();
		filesize   = bytes.getInt();
		imagedata  = bytes.getInt();
		unknown2   = bytes.getInt();
		unknown3   = bytes.getInt();
		index2     = bytes.getInt();
		unknown4   = bytes.getInt();
		index1     = bytes.getInt();
	}
}
