import requests
from bs4 import BeautifulSoup as bs

nickname = "하수언니"
response = requests.get("https://maplestory.nexon.com/Ranking/World/Total?c=" + nickname)
soup = bs(response.text, 'html.parser')
equipment_link = soup.select_one('.search_com_chk').select_one('a').get('href').replace('?p', '/Inventory?p', 1)

response = requests.get("https://maplestory.nexon.com/" + equipment_link)
soup = bs(response.text, 'html.parser')

items = soup.select_one('.tab03_con_wrap').select('.inven_item_memo_title > h1 > a')
'''
print(items[1])
test = items[1].get('href')

data = {'p': test.split('?p=', 1)[1], '_': 1622164595842}
headers = {
    'Host': 'maplestory.nexon.com',
    'X-Requested-With': 'XMLHttpRequest',
    'Connection': 'close'
    }
res = requests.get("https://maplestory.nexon.com/" + test, headers=headers)

soup = bs(res.json()['view'].replace('\r', ''), 'html.parser')
'''
