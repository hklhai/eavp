package cn.edu.ncut.eavp.service;


import cn.edu.ncut.eavp.service.base.BaseService;

public interface SystemService extends BaseService {

	public abstract String getTreeList();

	public abstract String modifyTreeData();

	public abstract String modifyTreeDataBatch();
	
	/******************************** 系统管理 开始 ********************************/
	public abstract String userLogin();
	public abstract String getOrganizationObjByOrgid();
	public abstract String getMyModel();
	
	public abstract String getAllRoleObj();
	public abstract String getRoleObjByRoleid();
	public abstract String saveRoleObj();
	public abstract String updateRoleObj();
	public abstract String deleteRoleObjByRoleid();

	public abstract String getModelObjByModelid();
	public abstract String updateModelObj();
	public abstract String shiftModelStatus();
	public abstract String getAllTopLevelModelObj();
	public abstract String saveModelObj();
	
	public abstract String getAllUserObj();
	public abstract String getVuserinfoObjByUserid();
	public abstract String transferUserDataaccessNames();
	public abstract String saveUserObj();
	public abstract String updateUserObj();
	//上岗、离岗 lh 2014-12-8 08:13:03
	public abstract String enableUserObj();
	public abstract String qiutUserObj();
	
	public abstract String deleteUserObjByUserid();
	public abstract String resetUserObjPassword();
	public abstract String userPasswordModify();

	public abstract String getAllModelObjByRoleid();
	public abstract String updateRolemodelObj();
	
	public abstract String getCodetreeinfoObjByNodeid();
	
	public abstract String getFilterwordObjByOrgid();
	public abstract String getFilterwordObjByFilterwordid();
	public abstract String getOrganizationObjLikeDataaccess();
	
	public abstract String saveOrganizationObj();
	public abstract String updateOrganizationObj();
	public abstract String deleteOrganizationObjByOrgid();
	
	public abstract String getPrefixListByPublishid();
	public abstract String savePrefixinfoObj();
	public abstract String openPrefixByPrefixid();
	public abstract String stopPrefixByPrefixid();
	public abstract String updatePrefixinfoObj();
	public abstract String deletePrefixByPrefixid();
	public abstract String getPrefixObjByPrefixid();
	
	public abstract String getPublishOrgInfoByOrgid();
	public abstract String updatePublishOrgInfo();
	/******************************** 系统管理 结束 ********************************/

	public abstract String integerateQuery();
	
	public abstract String expertAudit();
	
	public abstract String expertTopicAudit();
}



