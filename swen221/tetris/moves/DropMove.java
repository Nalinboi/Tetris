// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a "hard drop". That is, when the tetromino is immediately dropped
 * all the way down as far as it can go.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class DropMove implements Move {
	@Override
	public boolean isValid(Board board) {
		ActiveTetromino activeTetromino = board.getActiveTetromino();
		// Check whether it has landed
		if (activeTetromino != null) { //&& activeTetromino.isEnd(board)
			// apply gravity
			//activeTetromino = activeTetromino.translate(0, -1);
			return true;			
		} 
		return false;
	}

	@Override
	public Board apply(Board board) {
		//return null;
		board = new Board(board);
		
		ActiveTetromino tetromino = board.getActiveTetromino();
		while(board.canPlaceTetromino(tetromino)){
			tetromino = tetromino.translate(0, -1);	//apply gravity		
		}
		tetromino = tetromino.translate(0,  1); //translate it back once 		
		board.setActiveTetromino(tetromino); 
		return board;
	}

	@Override
	public String toString() {
		return "drop";
	}
}
