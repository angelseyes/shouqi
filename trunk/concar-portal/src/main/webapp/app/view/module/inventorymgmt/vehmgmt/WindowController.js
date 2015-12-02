Ext.define('BAICClassicApp.view.module.inventorymgmt.vehmgmt.WindowController', {
	extend: 'Ext.app.ViewController',
	init: function() {
		var alreadyUsed = false;


		Ext.apply(Ext.form.field.VTypes,{
		    uniqueIdentity:  function(v) {
		        Ext.Ajax.request({
					url: '/etruck-portal/web/driverprofile/validateuniqueidentity',
					method: 'POST',
					params: 'identity=' + v,
					success: function(o) {

						if (o.responseText == 0) {
							alreadyUsed = true;
						}  else {
							alreadyUsed = false;
						}

					}
				});
		        return !alreadyUsed;

		    },

		    uniqueIdentityText: 'Identity number already registed in system.'

		}) ;
		
		Ext.apply(Ext.form.field.VTypes,{
		    uniqueWorkId:  function(v) {
		        Ext.Ajax.request({
					url: '/etruck-portal/web/driverprofile/validateuniqueworkid',
					method: 'POST',
					params: 'workId=' + v,
					success: function(o) {

						if (o.responseText == 0) {
							alreadyUsed = true;
						}  else {
							alreadyUsed = false;
						}

					}
				});
		        return !alreadyUsed;

		    },

		    uniqueWorkIdText: 'Work id already registed in system.'

		}) ;
	},

	afterrender: function(window, options) {
		this.keyNav = new Ext.util.KeyMap({
			target: window.el,
			binding: [{
				key: "s",
				ctrl: true,
				defaultEventAction: 'preventDefault',
				fn: function() {
					this.save();
				},
				scope: this
			}]
		});
	},
	close: function() {
		this.getView().close();
	},
	resetPassword: function() {
		Ext.Msg.confirm("重置密码", "是否将密码重置为666888？", function(choice) {
			if (choice === 'yes') {
				var form = this.getView().down('form').getForm();
				Ext.Ajax.request({
					url: 'resetPw.json',
					params: {
						userName: form.getRecord().data.userName,
					},
					success: function(response) {
						this.getView().close();
					},
				});
			}
		}, this);
	},
	saveNewUser: function() {
		var values = this.getView().down('form').getValues();
		Ext.Ajax.request({
			url: 'saveNewUser.json',
			params: values,
			success: function(response) {
				this.getViewModel().getStore("myStore").load();
				this.getView().close();
			},
		});
	},
	saveExplorer: function() {
		Ext.Msg.alert('Status', '还没有实现！');
		this.getView().close();
	},
	save: function() {
		var form = this.getView().down('form').getForm();
		if (form.isValid()) {
			var record = form.getRecord().copy();
			form.updateRecord(record);
			if(this.checkMobile(record.data.tel) && this.isIdCardNo(record.data.citizenId)){
				record.save({
					success: function(r, op) {
						console.log(op._response.responseText);
						var resp = Ext.decode(op._response.responseText);
						if (resp.success) {
							this.getStore('myStore').reload();
							this.close();
						} else {
							Ext.MessageBox.show({
								title: resp.status,
								msg: resp.failureMsg,
								icon: Ext.MessageBox.ERROR,
								buttons: Ext.Msg.OK
							});
						}
					},
					failure: function(r, op) {
						Ext.MessageBox.show({
							title: 'REMOTE EXCEPTION',
							msg: op.getError(),
							icon: Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK
						});
					},
					scope: this
				});
			}
		}
	},
	checkMobile: function(sMobile){ 
		var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
        if(isMob.test(sMobile)||isPhone.test(sMobile)){
            return true;
        }else{
        	Ext.MessageBox.show({
                title: etruck.string.common.info,//'信息',
                msg: etruck.string.datacenter.drivermaindata.windows.telnumnotvalid,//'不是有效的电话号码！',
                icon: Ext.MessageBox.INFO,
                buttons: Ext.Msg.OK
            });
            return false;
        }
    },
    isIdCardNo: function(num) 
    {
        var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
        var error;
        var varArray = new Array();
        var intValue;
        var lngProduct = 0;
        var intCheckDigit;
        var intStrLen = num.length;
        var idNumber = num;    
        if ((intStrLen != 15) && (intStrLen != 18)) {
        	Ext.MessageBox.show({
                title: etruck.string.common.info,//'信息',
                msg: etruck.string.datacenter.drivermaindata.windows.idclenwrong,//'输入身份证号码长度不对！',
                icon: Ext.MessageBox.INFO,
                buttons: Ext.Msg.OK
            });
            return false;
        }    
        // check and set value
        for(i=0;i<intStrLen;i++) {
            varArray[i] = idNumber.charAt(i);
            if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            	Ext.MessageBox.show({
                    title: etruck.string.common.info,//'信息',
                    msg: etruck.string.datacenter.drivermaindata.windows.idcnumwrong,//'错误的身份证号码！',
                    icon: Ext.MessageBox.INFO,
                    buttons: Ext.Msg.OK
                });
                return false;
            } else if (i < 17) {
                varArray[i] = varArray[i]*factorArr[i];
            }
        }
        if (intStrLen == 18) {
            var date8 = idNumber.substring(6,14);
            if (this.checkDate(date8) == false) {
            	Ext.MessageBox.show({
                    title: etruck.string.common.info,//'信息',
                    msg: etruck.string.datacenter.drivermaindata.windows.idcdatewrong,//'身份证中日期信息不正确！',
                    icon: Ext.MessageBox.INFO,
                    buttons: Ext.Msg.OK
                });
                return false;
            }        
            // calculate the sum of the products
            for(i=0;i<17;i++) {
                lngProduct = lngProduct + varArray[i];
            }        
            // calculate the check digit
            intCheckDigit = 12 - lngProduct % 11;
            switch (intCheckDigit) {
                case 10:
                    intCheckDigit = 'X';
                    break;
                case 11:
                    intCheckDigit = 0;
                    break;
                case 12:
                    intCheckDigit = 1;
                    break;
            }        
            // check last digit
            if (varArray[17].toUpperCase() != intCheckDigit) {
            	Ext.MessageBox.show({
                    title: etruck.string.common.info,//'信息',
                    msg: etruck.string.datacenter.drivermaindata.windows.idcvalidinfowrong,//'身份证效验位错误!',
                    icon: Ext.MessageBox.INFO,
                    buttons: Ext.Msg.OK
                });
                return false;
            }
        }else{
        	//length is 15 & check date
            var date6 = idNumber.substring(6,12);
            if (this.checkDate(date6) == false) {
            	Ext.MessageBox.show({
                    title: etruck.string.common.info,//'信息',
                    msg: etruck.string.datacenter.drivermaindata.windows.idcdatewrong,//'身份证日期信息有误！',
                    icon: Ext.MessageBox.INFO,
                    buttons: Ext.Msg.OK
                });
                return false;
            }
        }
        return true;
    },
    checkDate: function(date){
    	//TODO check date validation
    	return true;
    },
    uploadCSV: function(){
		var form = Ext.getCmp('importCSVForm_drivermgnt');//this.up('form').getForm();
		if(form.isValid()){
			form.submit({
				url: '/etruck-portal/web/driver/importCSV',
				waitMsg: etruck.string.common.loading,//'正在上传...',
				success: function() {
					Ext.MessageBox.show({
	                    title: etruck.string.common.info,//'信息',
	                    msg: etruck.string.datacenter.drivermaindata.windows.importok,//'导入成功！',
	                    icon: Ext.MessageBox.INFO,
	                    buttons: Ext.Msg.OK
	                });
					Ext.getCmp('drivermaindata').getStore().reload();
					Ext.getCmp('drivermaindata').setSelection(null);
  				  	Ext.getCmp('drivermgntImportWindow').close();
		    	}
		    });
		}
	}
});