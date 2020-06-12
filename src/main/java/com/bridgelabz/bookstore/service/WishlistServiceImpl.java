package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.modelmapper.EntityToDtoMapper;
import com.bridgelabz.bookstore.repository.WishlistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WishlistServiceImpl implements IWishlistService  {

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public String addToWishlist(WishlistDto wishlistDto) {
        Wishlist wishlist = entityToDtoMapper.convertToWishlistEntity(wishlistDto);
        if(wishlistRepository.existsWishlistByBookId(wishlist.getBookId()) && wishlistRepository.existsWishlistByUserId(wishlist.getUserId()))
            wishlistRepository.deleteWishlistByBookIdAndUserId(wishlist.getBookId(), wishlist.getUserId());
        wishlistRepository.save(wishlist);
        return "Added to Wishlist successfully";
    }

    @Override
    public String removeFromWishlist(WishlistDto wishlistDto) {
        Wishlist wishlist = entityToDtoMapper.convertToWishlistEntity(wishlistDto);
        wishlistRepository.deleteWishlistByBookIdAndUserId(wishlist.getBookId(), wishlist.getUserId());
        return "Removed from Wishlist successfully";
    }
}