package org.filter;

import org.DAO.LikeDAO;
import org.entities.Like;

public class LikeFilter {

    private LikeDAO likeDAO= new LikeDAO();

    public boolean isReactedBefore(Like like){
        return likeDAO.optionalLike(like).isPresent();
    }
}
