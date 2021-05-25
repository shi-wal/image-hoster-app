package com.upgrad.technical.service.business;


import com.upgrad.technical.service.dao.ImageDao;
import com.upgrad.technical.service.dao.UserDao;
import com.upgrad.technical.service.entity.ImageEntity;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.exception.ImageNotFoundException;
import com.upgrad.technical.service.exception.UnauthorizedException;
import com.upgrad.technical.service.exception.UserNotSignedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

@Service
public class AdminService {

    @Autowired
    private ImageDao imageDao;

    public ImageEntity getImage(final String imageUuid, final String authorization) throws ImageNotFoundException, UnauthorizedException, UserNotSignedInException {

        UserAuthTokenEntity userAuthTokenEntity = imageDao.getUserAuthToken(authorization);

//        Handling the UserNotSignedInException
        if(userAuthTokenEntity == null){
            throw new UserNotSignedInException("SIGN-001","You're not signed in, sign in first to get your image deails!");
        }

        String role = userAuthTokenEntity.getUser().getRole();
        if (role.equals("admin")){
            ImageEntity imageEntity= imageDao.getImage(imageUuid);
//         Handling the ImageNotFoundException
            if (imageEntity == null){
                throw new ImageNotFoundException("IMG-001", "Image not found!");
            }
            return imageEntity;
        }
//        Handling the UnauthorizedException
        throw new UnauthorizedException("ATH-001", "You are not authorized to get the image!");
     }

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity updateImage(final ImageEntity imageEntity, final String authorization) throws ImageNotFoundException, UnauthorizedException, UserNotSignedInException {
        UserAuthTokenEntity userAuthTokenEntity = imageDao.getUserAuthToken(authorization);
//        Handling the UserNotSignedInException
        if(userAuthTokenEntity == null){
            throw new UserNotSignedInException("SIGN-001","You're not signed in, sign in first to get your image deails!");
        }

        String role = userAuthTokenEntity.getUser().getRole();
        if (role.equals("admin")){
            ImageEntity imageForUpdate = imageDao.getImage(imageEntity.getUuid());

//            Handling the ImageNotFoundException
            if (imageForUpdate == null ){
                throw new ImageNotFoundException("IMG-001", "Image not found!");
            }
           return imageDao.updateImage(imageEntity);
        }

//        Handling the UnauthorizedException
        throw new UnauthorizedException("ATH-002", "You are not authorized to update this image!");
    }
}
