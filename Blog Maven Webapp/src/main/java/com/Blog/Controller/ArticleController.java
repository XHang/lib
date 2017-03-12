package com.Blog.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Blog.Model.Article;
import com.Blog.Model.User;
import com.Blog.Service.ArticleService;
import com.Blog.Util.DownloadFileUtil;
@Controller
@RequestMapping("/article")
public class ArticleController {
	//书写博客
	ArticleService articleservice;
	@RequestMapping("/writeArticle")
	public ModelAndView writeArticle(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("writeBlog");
		return mv;
	}
	//保存博客
	@RequestMapping("/save")
	public String saveArticle(Article article){
		articleservice.save(article); 
		return "redirect:/user/intoSpace.do";
	}
	public ArticleService getArticleservice() {
		return articleservice;
	}
	//展现博客-暂时定为自己
	@RequestMapping("/read")
	public ModelAndView getArticle(int id){
		Article article=articleservice.getArticle(id);
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("article", article);
		 mv.setViewName("readBlog");
		 return mv;
	}
	@RequestMapping("/articleList")
	public ModelAndView articleListByCategory(int categoryId){
		List<Article> articles=articleservice.getArticleListByCategory(categoryId);
		ModelAndView mv=new ModelAndView();
		mv.addObject("articles", articles);
		mv.setViewName("ArticleList");
		return mv;
	}
	@RequestMapping("/search")
	public ModelAndView search(String keyWord){
		List <Article> articles=articleservice.search(keyWord);
		ModelAndView mv=new ModelAndView();
		mv.addObject("articles", articles);
		mv.setViewName("ArticleList");
		return mv;
	}
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}
	@RequestMapping("/getAllArticle.do")
	public ModelAndView getAllArticle(HttpSession httpSession){
		User user=(User)httpSession.getAttribute("user");
		List <Article> articles=articleservice.getAllArticle(user.getId());
		ModelAndView mv=new ModelAndView();
		mv.addObject("articles", articles);
		mv.setViewName("ArticleList");
		return mv;
		
	}
	@RequestMapping("motdify")
	public ModelAndView modity(int articleId){
		Article article=articleservice.getArticle(articleId);
		ModelAndView mv=new ModelAndView();
		mv.addObject("article",article);
		mv.setViewName("modifyBlog");
		return mv;
		
	}
	@RequestMapping("execModify")
	public String execModify(Article article){
		articleservice.update(article);
		return "redirect:/user/intoSpace.do";
	}
	@RequestMapping("delete")
	public String delete(int articleid){
		articleservice.deleteArticleById(articleid);
		return "redirect:/user/intoSpace.do";
	}
	//文章图片上传！
	@ResponseBody
	@RequestMapping(value="/imageupload",produces="application/json;charset=UTF-8")
	public String imageupload(@RequestParam("editormd-image-file")  MultipartFile Imgfile,HttpServletRequest request,HttpServletResponse response) {
		String filename=Imgfile.getOriginalFilename();
		String path=request.getSession().getServletContext().getRealPath("")+"/resources/ArticleImages/"+filename;
		BufferedOutputStream bos=null;
		BufferedInputStream bis=null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			bis = new BufferedInputStream(Imgfile.getInputStream());
			int leng;
			while((leng=bis.read())!=-1){
				bos.write(leng);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(bis!=null){
					bis.close();
				}
				if(bos!=null){
					bos.close();	//关闭自动刷新流

				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
		return  "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"http://localhost:8080/Blog/resources/ArticleImages/"+filename+"\"}";
	}
	//下载文件，需要传参数：文件名
	@RequestMapping("downloadFile")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,String filename) throws UnsupportedEncodingException{
		String path=request.getSession().getServletContext().getRealPath("/resources/download")+"\\";	//获取被下载文件的目录，暂时定为网站根目录的upload文件夹
		DownloadFileUtil.downloadFile(filename,path,response);					//传入文件名，文件类型（不需要），文件目录，response来下载文件。
		
		
	}
}
