// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.tetromino;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;

/**
 * Represents a tetromino which has not yet been placed on the board. That is, a
 * tetromino which is "in flight" and has a center position on the board. Once a
 * tetrmino has been placed, it loses the concept of a center position as this
 * no longer makes sense.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class ActiveTetromino implements Tetromino {
	/**
	 * The tetromino underlying this active tetromino. This determins the shape,
	 * color and orientation of this tetromino.
	 */
	private final Tetromino tetromino;

	/**
	 * Column coordinate of the center of the Tetromino
	 */
	protected final int x;

	/**
	 * Row coordinate of the center of the Tetromino
	 */
	protected final int y;

	public ActiveTetromino(int x, int y, Tetromino tetromino) {
		if (tetromino == null) {
			throw new IllegalArgumentException("invalid tetromino!");
		}
		this.x = x;
		this.y = y;
		this.tetromino = tetromino;
	}

	@Override
	public Color getColor() {
		return tetromino.getColor();
	}

	@Override
	public Orientation getOrientation() {
		return tetromino.getOrientation();
	}

	/**
	 * Get the abstract tetromino underlying this active tetromino.
	 *
	 * @return
	 */
	public Tetromino getUnderlyingTetromino() {
		return tetromino;
	}

	@Override
	public boolean isWithin(int x, int y) {
		int dx = x - this.x;
		int dy = y - this.y;
		//
		switch (tetromino.getOrientation()) {
		case NORTH:
			return tetromino.isWithin(dx, dy);
		case SOUTH:
			return tetromino.isWithin(-dx, -dy);
		case EAST:
			return tetromino.isWithin(-dy, dx);
		case WEST:
		default:
			return tetromino.isWithin(dy, -dx);
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle box = tetromino.getBoundingBox();
		switch (tetromino.getOrientation()) {
		case WEST:
			box = box.rotateClockwise();
		case SOUTH:
			box = box.rotateClockwise();
		case EAST:
			box = box.rotateClockwise();
		}
		return box.translate(x, y);
	}
	
//	public boolean isEnd (Board board) {
//		//return new ActiveTetromino(x + dx, y + dy, tetromino);
//		Rectangle r = board.getActiveTetromino().getBoundingBox();
//		if(r.getMinY()<=0) {
//			return true;
//		}
//		for(int row = r.getMinY(); row <= r.getMaxY(); row++) { //row = x
//			for(int col = r.getMinX(); col <= r.getMaxX(); col++) {	 //col = y			
//				if(this.isWithin(row, col) && board.getPlacedTetrominoAt(row, col-1) !=null) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	public boolean canGoLeft (Board board) {
//		board = new Board(board);
//		
//		ActiveTetromino activeTetromino = this;
//		activeTetromino = activeTetromino.translate(-1, 0);
//		
//		if(activeTetromino.getBoundingBox().getMinX() < 0) {
//			return false;
//		}
//		return true;
		
		board = new Board(board);
		
		ActiveTetromino activeTetromino = this;
		activeTetromino = activeTetromino.translate(-1, 0);
		//ActiveTetromino testTetromino = activeTetromino;
		// Check whether it has landed
		//checks if bounding box doesnt go out before the start of the x width
		if (activeTetromino.getBoundingBox().getMinX() < 0) { 
			return false;
		}
		
		for(int row = 0; row < board.getWidth(); row++) { //row = x
			for(int col = 0; col < board.getHeight(); col++) {	 //col = y			
				if(board.getPlacedTetrominoAt(row, col) != null && board.getPlacedTetrominoAt(row, col) != this && activeTetromino.isWithin(row, col)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canGoRight (Board board) {
//		board = new Board(board);
//		
//		ActiveTetromino activeTetromino = this;
//		activeTetromino = activeTetromino.translate(1, 0);
//		
//		if(activeTetromino.getBoundingBox().getMinX() >= board.getWidth()) {
//			return false;
//		}
//		return true;
		
		board = new Board(board);
		
		ActiveTetromino activeTetromino = this;
		activeTetromino = activeTetromino.translate(1, 0);
		//ActiveTetromino testTetromino = activeTetromino;
		//checks if bounding box doesnt go beyond the width of the screen after going right
		if (activeTetromino.getBoundingBox().getMaxX() >= board.getWidth()) { 
			return false;
		}
		
		for(int row = 0; row < board.getWidth(); row++) { //row = x
			for(int col = 0; col < board.getHeight(); col++) {	 //col = y			
				if(board.getPlacedTetrominoAt(row, col) != null && board.getPlacedTetrominoAt(row, col) != this && activeTetromino.isWithin(row, col)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canRotate (Board board) {
//		board = new Board(board);
//		
//		ActiveTetromino activeTetromino = this;
//		activeTetromino = activeTetromino.rotate(1);
//		
//		if((activeTetromino.getBoundingBox().getMinX() >= board.getWidth()) || (activeTetromino.getBoundingBox().getMinX() < 0)) {
//			return false;
//		}
//		if(activeTetromino.getBoundingBox().getMinY() < 0) {
//			return false;
//		}
//		return true;
		
		board = new Board(board);
		
		ActiveTetromino activeTetromino = this;
		activeTetromino = activeTetromino.rotate(1);
		//ActiveTetromino testTetromino = activeTetromino;
		//all the checks for down, left and right
		if((activeTetromino.getBoundingBox().getMinX() >= board.getWidth()) || (activeTetromino.getBoundingBox().getMinX() < 0)) {
			return false;
		}
		if(activeTetromino.getBoundingBox().getMinY() < 0) {
			return false;
		}
		
		for(int row = 0; row < board.getWidth(); row++) { 
			for(int col = 0; col < board.getHeight(); col++) {				
				if(board.getPlacedTetrominoAt(row, col) != null && board.getPlacedTetrominoAt(row, col) != this && activeTetromino.isWithin(row, col)) {
					return false;
				}
			} //we are checking if everything in the whole board is good
		}
		return true;
	}
	
	public boolean canGoDown (Board board) {		
//		board = new Board(board);
//		
//		ActiveTetromino activeTetromino = this;
//		activeTetromino = activeTetromino.translate(0, 1);
//		
//		if(activeTetromino.getBoundingBox().getMinY() > 0) {
//			return false;
//		}
//		return true;
		
		board = new Board(board);
		
		ActiveTetromino activeTetromino = this;
		activeTetromino = activeTetromino.translate(0, -1);
		//ActiveTetromino testTetromino = activeTetromino;
		// Check whether it has landed
		if (activeTetromino.getBoundingBox().getMinY() < 0) { 
			return false;
		}
		
		for(int row = 0; row < board.getWidth(); row++) { //row = x
			for(int col = 0; col < board.getHeight(); col++) {	 //col = y			
				if(board.getPlacedTetrominoAt(row, col) != null && board.getPlacedTetrominoAt(row, col) != this && activeTetromino.isWithin(row, col)) {
					return false;
				} //checks if every cell is fine within the board 
			}
		}
		return true;
	}
	


	/**
	 * Move this Tetromino by a given amount in the x and/or y direction.
	 *
	 * @param dx
	 *            The amount to move in the x direction.
	 * @param dy
	 *            The amount to move in the y direction.
	 * @return
	 */
	public ActiveTetromino translate(int dx, int dy) {
		return new ActiveTetromino(x + dx, y + dy, tetromino);
	}

	@Override
	public ActiveTetromino rotate(int steps) {
		return new ActiveTetromino(x, y, tetromino.rotate(steps));
	}

	@Override
	public String toString() {
		return tetromino.toString();
	}

	@Override
	public String getName() {
		return tetromino.getName();
	}
}
