package com.example.blogApplication.serviceImpl;

import com.example.blogApplication.dtos.PostDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Category;
import com.example.blogApplication.model.Post;
import com.example.blogApplication.model.User;
import com.example.blogApplication.repositories.CategoryRepository;
import com.example.blogApplication.repositories.PostRepository;
import com.example.blogApplication.repositories.UserRepository;
import com.example.blogApplication.responses.PostPageResponse;
import com.example.blogApplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) throws RohanException {

        Post postToBeAdded = modelMapper.map(postDTO,Post.class);

        User user = userRepository.findById(userId).orElseThrow(()->new RohanException("User","Id",userId));

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new RohanException("Category","Id",categoryId));

        postToBeAdded.setPostedOn(new Date());
        postToBeAdded.setUser(user);
        postToBeAdded.setCategory(category);

        postToBeAdded = postRepository.save(postToBeAdded);

        return modelMapper.map(postToBeAdded, PostDTO.class);

    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) throws RohanException {

        User user = userRepository.findById(userId).orElseThrow(()->new RohanException("User","Id",userId));

        List<Post> posts = postRepository.findByUser(user);

        List<PostDTO> postDTOS = posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDTOS;

    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) throws RohanException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new RohanException("Category","Id",categoryId));

        List<Post> posts = postRepository.findByCategory(category);

        List<PostDTO> postDTOS = posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDTOS;
    }

    @Override
    public PostDTO updatePost(Integer id, PostDTO postDTO) throws RohanException {

        Post post = postRepository.findById(id).orElseThrow(()->new RohanException("Post","ID",id));

        post.setCategory(modelMapper.map(postDTO.getCategory(),Category.class));
        post.setUser(modelMapper.map(postDTO.getUser(),User.class));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setPostedOn(new Date());

        post = postRepository.save(post);

        return modelMapper.map(post,PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Integer Id) throws RohanException {

        Post post = postRepository.findById(Id).orElseThrow(()->new RohanException("Post","ID",Id));

        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostPageResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        PostPageResponse response = new PostPageResponse();
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> pageOfPosts = postRepository.findAll(pageable);

        List<Post> posts = pageOfPosts.getContent();

        response.setData(posts.stream().map(post -> modelMapper.map(post,PostDTO.class)).collect(Collectors.toList()));
        response.setLast(pageOfPosts.isLast());
        response.setPageNumber(pageOfPosts.getNumber());
        response.setPageSize(pageOfPosts.getSize());
        response.setTotalItems((int) pageOfPosts.getTotalElements());
        response.setTotalPages(pageOfPosts.getTotalPages());

        return response;
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {

         List<Post> posts = postRepository.findByTitleContaining(keyword);

        return posts.stream().map(post -> modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

    }


}
