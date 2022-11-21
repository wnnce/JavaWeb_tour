from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import psycopg2
import time
from datetime import datetime
chrome_options = Options()
# selenium隐藏
chrome_options.add_experimental_option('useAutomationExtension', False)
chrome_options.add_experimental_option('excludeSwitches', ["enable-automation"])
chrome_options.add_argument('--disable-blink-features=AutomationControlled')
chrome_options.add_argument('user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36')


# 插入数据库
def insert_db(scenic):
    conn = psycopg2.connect(database="zhsx", user="lisang", password="00000...", host="120.46.151.166", port="5432")
    cursor = conn.cursor()
    date = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    sql = "insert into t_scenic(title,content,img_url,scenic_date) values (\'"+scenic[0]+"\',\'"+scenic[1]+"\',\'"+scenic[2]+"\',\'"+date+"\')"
    try:
        cursor.execute(sql)
        conn.commit()
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


# 主方法运行
if __name__ == '__main__':
    driver = webdriver.Chrome(options=chrome_options)
    # 执行js 清除selenium的标识
    with open('js/stealth.min.js', 'r') as f:
        js = f.read()
    driver.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {"source": js})
    driver.maximize_window()
    driver.implicitly_wait(30)
    driver.get("https://www.mafengwo.cn/jd/10208/gonglve.html")
    time.sleep(2)
    # 进入循环
    while True:
        ul = driver.find_element(By.XPATH, '//*[@id="container"]/div[5]/div/div[1]/ul')
        # 获取景点列表
        li_list = ul.find_elements(By.TAG_NAME, 'li')
        for li in li_list:
            scenic = []
            # 保存当前窗口
            now_handle = driver.current_window_handle
            li.click()
            time.sleep(2)
            # 将句柄切换到新打开的景点详情窗口
            driver.switch_to.window(driver.window_handles[len(driver.window_handles) - 1])

            # 景点列表里面有一部分不是景点 进行判断
            try:
                text = driver.find_element(By.CLASS_NAME, 'container').get_attribute('data-cs-t')
            except Exception as e:
                text = driver.find_element(By.CLASS_NAME, 'col-main').get_attribute('data-cs-t')
            if text == '景点详情页':
                # 获取景点标题
                title = driver.find_element(By.XPATH, '/html/body/div[2]/div[2]/div/div[3]/h1').text
                img_div = driver.find_element(By.XPATH, '/html/body/div[2]/div[3]/div[1]/div/a/div')
                # 获取第一张图片
                img_url = img_div.find_elements(By.TAG_NAME, 'img')[0].get_attribute('src')
                # 捕获异常 有的景点没有详情
                try:
                    content = driver.find_element(By.CLASS_NAME, 'summary').text
                except Exception as e:
                    content = ''
                # 添加到数组
                scenic.append(title)
                scenic.append(content)
                scenic.append(img_url)
                time.sleep(1)
                # 插入到数据库
                insert_db(scenic)
                # 关闭当前窗口
                driver.close()
            else:
                driver.close()
            # 句柄切换到景点列表窗口
            driver.switch_to.window(now_handle)
            time.sleep(3)
        # 获取下一页按钮 存在则点击 不存在则退出循环
        next_btn = driver.find_element(By.CLASS_NAME, 'pg-next')
        if next_btn:
            next_btn.click()
            time.sleep(5)
        else:
            driver.quit()
            break
