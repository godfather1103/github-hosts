package sync;import org.jsoup.Jsoup;import org.jsoup.nodes.Document;import org.jsoup.nodes.Element;import java.io.*;import java.text.DateFormat;import java.util.*;/** * <p>Title:        Godfather1103's Github</p> * <p>Copyright:    Copyright (c) 2023</p> * <p>Company:      https://github.com/godfather1103</p> * * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com * @version 1.0 * @date 创建时间：2023/9/15 15:10 * @since 1.0 */public class Update {    private static final String TAG_A = "a";    private static final String FILE_PATH = System.getProperty("user.dir") + "/README.md";    private static final String HOSTS_PATH = System.getProperty("user.dir") + "/hosts";    private static final String TEMPLATE_PATH = System.getProperty("user.dir") + "/template.md";    private static final List<String> GITHUB_URLS = Arrays.asList(            "github.githubassets.com",            "central.github.com",            "desktop.githubusercontent.com",            "assets-cdn.github.com",            "camo.githubusercontent.com",            "github.map.fastly.net",            "github.global.ssl.fastly.net",            "gist.github.com",            "github.io",            "github.com",            "api.github.com",            "raw.githubusercontent.com",            "user-images.githubusercontent.com",            "favicons.githubusercontent.com",            "avatars5.githubusercontent.com",            "avatars4.githubusercontent.com",            "avatars3.githubusercontent.com",            "avatars2.githubusercontent.com",            "avatars1.githubusercontent.com",            "avatars0.githubusercontent.com",            "avatars.githubusercontent.com",            "codeload.github.com",            "github-cloud.s3.amazonaws.com",            "github-com.s3.amazonaws.com",            "github-production-release-asset-2e65be.s3.amazonaws.com",            "github-production-user-asset-6210df.s3.amazonaws.com",            "github-production-repository-file-5c1aeb.s3.amazonaws.com",            "githubstatus.com",            "github.community",            "media.githubusercontent.com",            "copilot-proxy.githubusercontent.com",            "cloud.githubusercontent.com",            "pipelines.actions.githubusercontent.com",            "objects.githubusercontent.com"    );    private static final String IP_ADDRESS_BASE_URL = "https://sites.ipaddress.com/%s/";    public static String resolveUrl(String url) {        return String.format(IP_ADDRESS_BASE_URL, url);    }    public static String lJust(String str, int total, String pad) {        String res = String.format("%-" + total + "s", str);        res = res.replaceAll("\\s", pad);        return res;    }    public static List<String> findWrapper(String host) {        int retryCount = 3;        List<String> result = new ArrayList<>(0);        while (true) {            if (retryCount <= 0) {                System.out.println(host + " is failed.");                break;            }            try {                result = findIp(host);                if (!result.isEmpty()) {                    break;                }            } catch (Exception e) {                System.out.println("findIp has a error:" + e);            }            retryCount--;        }        return result;    }    public static List<String> findIp(String host) throws Exception {        String url = resolveUrl(host);        System.out.println("request url = " + url);        Document doc = Jsoup.connect(url)                .userAgent(                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.864.37")                .timeout(20000)                .get();        List<String> ip = new ArrayList<>(0);        for (Element element : doc.getElementsByTag(TAG_A)) {            String text = element.text();            if (text.matches("(\\d{1,3}\\.){3}\\d{1,3}")) {                ip.add(text);            }        }        System.out.println("parse host = " + String.join(";", ip));        return ip;    }    public static void main(String[] args) {        StringBuilder generateContent = new StringBuilder();        for (String host : GITHUB_URLS) {            List<String> result = findWrapper(host);            result.forEach(                    item -> generateContent                            .append(lJust(item, 30, " "))                            .append(host).append("\n")            );        }        System.out.println(generateContent);        String fileContent = readFileContent(TEMPLATE_PATH, generateContent.toString());        writeFile(FILE_PATH, fileContent);        writeFile(HOSTS_PATH, getHostsContent(generateContent.toString()));    }    public static String getProperty(String key) {        // 读取本地properties属性文件(重要)        Properties properties = new Properties();        try {            //把本地的properties属性文件转换成Java对象(流对象)返回,参数为配置文件的地址,地址只需要从bao路径开始            InputStream is = Thread.currentThread().getContextClassLoader().                    getResourceAsStream("config.properties");            //加载配置信息            //load  抛出异常，try  catch  捕获异常            properties.load(is);        } catch (IOException e) {            e.printStackTrace();        }        return properties.getProperty(key);    }    public static String getHostsContent(String content) {        return "# GitHub Host Start\n\n"                + content                + System.getProperty("line.separator")                + "# Please Star: https://github.com/godfather1103/github-hosts"                + System.getProperty("line.separator")                + "# Please Star: https://gitcode.net/godfather1103/github-hosts"                + System.getProperty("line.separator")                + "# Please Star: https://gitee.com/godfather1103/github-hosts"                + System.getProperty("line.separator")                + "# Update at: " + DateFormat.getDateTimeInstance().format(new Date())                + System.getProperty("line.separator") + "\n"                + "# GitHub Host End";    }    public static String readFileContent(String filePath, String content) {        BufferedReader br = null;        String line;        //保存修改过后的所有内容，不断增加        StringBuilder bufAll = new StringBuilder();        try {            br = new BufferedReader(new FileReader(filePath));            while ((line = br.readLine()) != null) {                StringBuilder buf = new StringBuilder();                //修改内容核心代码                if (line.startsWith("# GitHub Host Start")) {                    buf.append(line).append("\n\n");                    do {                        line = br.readLine();                    } while (!line.startsWith("# Please Star"));                    buf.append(content);                    buf.append(System.getProperty("line.separator"));                    buf.append(line);                } else if (line.startsWith("# Update at")) {                    buf.append(line);                    buf.replace(13, line.length(), DateFormat.getDateTimeInstance().format(new Date()));                } else if (line.startsWith("内容定时更新，最近更新时间：")) {                    buf.append(line);                    buf.replace(14, line.length(), DateFormat.getDateTimeInstance().format(new Date()));                } else {                    buf.append(line);                }                buf.append(System.getProperty("line.separator"));                bufAll.append(buf);            }        } catch (Exception e) {            e.printStackTrace();        } finally {            if (br != null) {                try {                    br.close();                } catch (IOException e) {                    e.printStackTrace();                }            }        }        return bufAll.toString();    }    /**     * 写回文件<BR>     *     * @param filePath 参数     * @param content  参数     * @return 结果     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com     * @date 创建时间：2023/9/15 15:10     */    public static void writeFile(String filePath, String content) {        BufferedWriter bw = null;        try {            bw = new BufferedWriter(new FileWriter(filePath));            bw.write(content);        } catch (Exception e) {            e.printStackTrace();        } finally {            if (bw != null) {                try {                    bw.close();                } catch (IOException e) {                    e.printStackTrace();                }            }        }    }}