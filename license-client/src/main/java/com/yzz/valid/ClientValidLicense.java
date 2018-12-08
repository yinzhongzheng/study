package com.yzz.valid;

import com.yzz.bean.ClientValidConfigBean;
import com.yzz.manager.LicenseManagerHolder;
import com.yzz.reader.PropertyClientReader;
import de.schlichtherle.license.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.prefs.Preferences;

/**
 * describe:客户端验证License
 * E-mail:yzzstyle@163.com  date:2018/12/5
 *
 * @Since 0.0.1
 */
public class ClientValidLicense {

    private static final String Def_CONF_PATH = "client-valid.properties";

    private int installStatus = 0;

    public ClientValidLicense(String onlyKey,String licDir) {
       new ClientValidLicense(Def_CONF_PATH,onlyKey,licDir);
    }

    public ClientValidLicense(String confPath, String onlyKey,String licDir) {
        PropertyClientReader reader = new PropertyClientReader();
        ClientValidConfigBean clientValidConfigBean = reader.read(confPath);
        clientValidConfigBean.setOnlykey(onlyKey);
        //初始licenseManger
        LicenseParam param = initLicenseParams(clientValidConfigBean);
        LicenseManagerHolder.initManager(param);
        //安装证书
        installStatus = install(licDir);
    }

    /**
     * 初始化参数
     * @param clientValidConfigBean
     * @return
     */
    private LicenseParam initLicenseParams(ClientValidConfigBean clientValidConfigBean) {
        Class<ClientValidLicense> clazz = ClientValidLicense.class;
        Preferences pre = Preferences.userNodeForPackage(clazz);
        CipherParam cipherParam = new DefaultCipherParam(clientValidConfigBean.getKeyStorePwd());
        KeyStoreParam pubStoreParam = new DefaultKeyStoreParam(
                clazz, clientValidConfigBean.getPubPath(),clientValidConfigBean.getPubAlias(), clientValidConfigBean.getKeyStorePwd(), null);

        LicenseParam licenseParam = new DefaultLicenseParam(
                clientValidConfigBean.getOnlykey(), pre, pubStoreParam, cipherParam);
        return licenseParam;
    }

    /**
     * 安装license
     * @param licDir
     * @return
     */
    public int install(String licDir) {
        try {
            LicenseManager licenseManager = LicenseManagerHolder.getLicenseManager();
            File file = new File(licDir);
            licenseManager.install(file);
            return 1;
        } catch (Exception e) {
            if (e instanceof LicenseContentException){
                return 0;
            }else {
                return -1;
            }
        }
    }

    /**
     * -1:证书安装失败
     * 0:证书过期
     * 1:验证成功
     * 2:验证失败
     * @return
     */
    public int vertify() {
        try {
            if (installStatus == -1){
                return -1;
            }else if(installStatus == 0){
                return 0;
            }
            LicenseManagerHolder.getLicenseManager().verify();
            return 1;
        } catch (LicenseContentException ex) {
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

    }

    public static void main(String[] args) {
        ClientValidLicense clientValidLicense = new ClientValidLicense("GADQB","D:\\key\\lic\\license.lic");
        while (true){
            try {
                System.out.println(clientValidLicense.vertify());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
