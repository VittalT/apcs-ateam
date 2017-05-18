import java.util.ArrayList;

public class B_King extends Piece
{
    public B_King(String a)
    {
        super(a);
    }
    
    public B_King(int a, int b)
    {
        super(a, b);
    }
    
    public boolean isSafeMove(String start, String end, Piece[][] board)
    {
        // ASSUMES isValidEndSquare() returns true
        if(start != null && end != null) {
            for(Piece[] pArr: board) {
                for(Piece p: pArr) {
                    if(isAWhitePiece(p)) {
                        for(String str: p.getPossibleMoves(board)) {
                            if(end.equals(str))
                                return false;
                        }
                        
                        for(String str: p.getPossibleCaptures(board)) {
                            if(end.equals(str))
                                return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<String> getPossibleMoves(Piece[][] board)
    {
        ArrayList<String> allPositions = new ArrayList<String>();
        int[] position = super.convertToChessInt(super.getPosition());
        for(int a = -1; a <= 1; a ++) {
            for(int b = -1; b <= 1; b ++) {
                if(!(a == 0 && b == 0) && (position[0] + a <= 8 && position[1] + b <= 8 && position[0] + a >= 1 && position[1] + b >= 1) && (board[position[0] + a][position[1] + b] == null) && isSafeMove(super.getPosition(), convertToString(position[0] + a, position[1] + b), board))
                    allPositions.add(convertToString(position[0] + a, position[1] + b));
            }
        }
        
        ArrayList<String> captures = getPossibleCaptures(board);
        for(int i = 0; i < captures.size(); i++) {
            allPositions.add(captures.get(i));
        }
        
        return allPositions;
    }
    
    public ArrayList<String> getPossibleCaptures(Piece[][] board)
    {
        ArrayList<String> allCaptures = new ArrayList<String>();
        int[] position = super.convertToChessInt(super.getPosition());
        for(int a = -1; a <= 1; a ++) {
            for(int b = -1; b <= 1; b ++) {
                if(!(a == 0 && b == 0) && (position[0] + a <= 8 && position[1] + b <= 8 && position[0] + a >= 1 && position[1] + b >= 1) && isAWhitePiece(board[position[0] + a][position[1] + b]) && isSafeMove(super.getPosition(), convertToString(position[0] + a, position[1] + b), board))
                    allCaptures.add(convertToString(position[0] + a, position[1] + b));
            }
        }
        
        return allCaptures;
    }
}
