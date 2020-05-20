package filter;

import DAO.LikeDAO;
import entities.Like;

public class LikeFilter {

    private LikeDAO likeDAO= new LikeDAO();

    public boolean isReactedBefore(Like like){
        return likeDAO.optionalLike(like).isPresent();
    }
}
