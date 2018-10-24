package com.lym.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.lym.entity.PageBean;
import com.lym.entity.Product;
import com.lym.entity.ProductBigType;
import com.lym.entity.ProductSmallType;
import com.lym.service.ProductBigTypeService;
import com.lym.service.ProductSmallTypeService;
import com.lym.utli.DateUtil;
import com.lym.utli.ResponseUtil;
import com.lym.utli.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
public class ProductBigTypeAction extends ActionSupport  {
	
	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	private String rows;
	private String total;
	private ProductBigType s_productBigType;
	private String page;
	
	private String ids;
	
	private ProductBigType productBigType;
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ProductBigType getProductBigType() {
		return productBigType;
	}

	public void setProductBigType(ProductBigType productBigType) {
		this.productBigType = productBigType;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public ProductBigType getS_productBigType() {
		return s_productBigType;
	}

	public void setS_productBigType(ProductBigType s_productBigType) {
		this.s_productBigType = s_productBigType;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String comboList() throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<ProductBigType> productBigTypeList=productBigTypeService.findAllBigTypeList();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
		JSONArray rows=JSONArray.fromObject(productBigTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
	
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<ProductBigType> productBigTypeList=productBigTypeService.findBigTypeList(s_productBigType, pageBean);
		long total=productBigTypeService.getCountBigType(s_productBigType);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
		JSONArray rows=JSONArray.fromObject(productBigTypeList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String save() throws Exception{
		productBigTypeService.saveBigType(productBigType);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(productSmallTypeService.existSmallTypeWithBigTypeId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "商品大类包含商品小类");
			}else{
				ProductBigType productBigType=productBigTypeService.getProductBigTypeById(Integer.parseInt(idsStr[i]));
				productBigTypeService.deleteBigType(productBigType);			
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
}
