/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.game.twoplayer.chess.ui;

import com.barrybecker4.game.common.board.BoardPosition;
import com.barrybecker4.game.common.ui.viewer.GameBoardViewer;
import com.barrybecker4.game.common.ui.viewer.ViewerMouseListener;
import com.barrybecker4.game.twoplayer.checkers.ui.CheckersLikeViewerMouseListener;
import com.barrybecker4.game.twoplayer.checkers.ui.CheckersViewerMouseListener;
import com.barrybecker4.game.twoplayer.chess.ChessBoard;
import com.barrybecker4.game.twoplayer.chess.ChessController;
import com.barrybecker4.game.twoplayer.chess.ChessMove;
import com.barrybecker4.game.twoplayer.chess.ChessPiece;

import java.util.List;

/**
 *  Mouse handling for chess game.
 *
 *  @author Barry Becker
 */
public class ChessViewerMouseListener extends CheckersLikeViewerMouseListener<ChessMove, ChessBoard> {

    /**
     * Constructor.
     */
    public ChessViewerMouseListener(GameBoardViewer<ChessMove, ChessBoard> viewer) {
        super(viewer);
    }

    @Override
    protected List<ChessMove> getPossibleMoveList(BoardPosition position) {
        ChessBoard board = (ChessBoard)viewer_.getBoard();
        ChessController controller = (ChessController)viewer_.getController();

        ChessPiece piece = (ChessPiece)position.getPiece();
        List<ChessMove> possibleMoveList =
            piece.findPossibleMoves(board, position.getRow(), position.getCol(),
                                    controller.getLastMove());
        controller.removeSelfCheckingMoves(possibleMoveList);
        return possibleMoveList;
    }

    @Override
    protected boolean customCheckFails(BoardPosition position, BoardPosition destp) {
       // intentionally do nothing.
       return false;
    }
}