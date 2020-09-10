import requests

import json

url = 'https://covid-dashboard.aminer.cn/api/events/list?type=event&page=1&size=50000'

# url = 'https://covid-dashboard.aminer.cn/api/dist/events.json'

r = requests.get(url)

a = r.json()
# print(type(a))
# print(len(a))
# print(len(a['data']))

events = []

num = 0
with open('events.txt', 'w', encoding='utf-8') as out_f:
    for data in a['data']:
        events.append(data['title'])
        out_f.write(data['title'].replace('\n', ' ') + '\n')
# print(num)
print(len(events))
