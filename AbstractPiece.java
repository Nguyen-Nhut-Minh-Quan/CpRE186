
package hw4;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Arrays;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

/**
 * Abstract superclass for implementations of the Piece interface.
 * @author Chillguy
 */
public abstract class AbstractPiece implements Piece {
	/**
	 * Position of this Piece
	 */
	private Position position;

	/**
	 * Cells of this Piece
	 */
	private Cell[] cells;

	/**
	 * Constructs a piece with the given position. Subclasses extending this class
	 * MUST call setCells to initialize initial cell positions and icons.
	 * Intialize cells array with the default set up : red Icon and position (0,0)
	 * @param position initial position for upper-left corner of bounding box
	 */
	protected AbstractPiece(Position position) {
		this.position = position;
		cells = new Cell[4];
		for (int i = 0; i < 4; i++) {
			cells[i] = new Cell(new Icon(Color.RED), new Position(0, 0));
		}
	}

	// YOUR CODE HERE TO IMPLEMENT THE Piece INTERFACE

	@Override
	public Piece clone() {
		try {
			AbstractPiece s = (AbstractPiece) super.clone();

			// make it into a deep copy (note there is no need to copy the position,
			// since Position is immutable)
			s.cells = new Cell[cells.length];
			for (int i = 0; i < cells.length; ++i) {
				s.cells[i] = new Cell(cells[i]);
			}
			return s;
		} catch (CloneNotSupportedException e) {
			// can't happen
			return null;
		}
	}

	@Override
	public Cell[] getCellsAbsolute() {
		// TODO Auto-generated method stub
		Cell[] ret = new Cell[cells.length];//Array of cell to store the cells with their absolute position
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];//get the cell in the grid
			/*
			 * Add the cells' within grid position with it position on the screen--> get the abslolute position
			 */
			int row = c.getRow() + position.row();
			int col = c.getCol() + position.col();
			/*
			 * add the cells to the array.
			 */
			Position p = new Position(row, col);
			ret[i] = new Cell(c.getIcon(), p);
		}
		return ret;
	}

	@Override
	public void setCells(Cell[] givenCells) {// inheritance
		cells = new Cell[givenCells.length];
		for (int i = 0; i < givenCells.length; i++) {
			cells[i] = new Cell(givenCells[i]);
		}

	}

	@Override
	public void shiftDown() {
		this.position = new Position(position.row() + 1, position.col());
	}

	@Override
	public void shiftLeft() {// inheritance
		this.position = new Position(position.row(), position.col() - 1);
	}

	@Override
	public void shiftRight() {// inheritance
		// TODO Auto-generated method stub
		this.position = new Position(position.row(), position.col() + 1);
	}

	@Override
	public Cell[] getCells() {
		// TODO Auto-generated method stub
		Cell[] deepcopy = new Cell[4];
		for (int i = 0; i < 4; i++) {
			deepcopy[i] = new Cell(cells[i].getIcon(), new Position(cells[i].getRow(), cells[i].getCol()));
		}
		return deepcopy;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void cycle() {
		Icon get = cells[3].getIcon();//Initlialized with the icon of the cell at index 3--> swap it to the first cell
		Icon give;//used as a dummy for swapping.
		/*
		 * Swap the icon of the cell to the one that is one higher than it: 1 take 0, 2 take 2, 3 take 2, and 0 take 3
		 */
		for (Cell c : cells) {
			give = c.getIcon();//update the dummy
			c.setIcon(get);//update the icon
			get = give;
		}
	}
}
