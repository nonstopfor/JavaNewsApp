import jieba
from gensim import corpora, models
from collections import defaultdict
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

np.random.seed(4568239)
with open('vocab/stopwords.txt', encoding='utf-8') as f:
    stopwords = [line.strip() for line in f.readlines()]
    stopwords.extend([' '])


def remove_stopwords(ls):  # 去除停用词
    return [word for word in ls if word not in stopwords]


with open('events.txt', 'r', encoding='utf-8') as f:
    lines = f.readlines()
    words_ls = []
    for line in lines:
        title = line.split('###')[0][1:]
        words_ls.append(remove_stopwords(jieba.lcut(title)))

    dict = corpora.Dictionary(words_ls)
    corpus = [dict.doc2bow(words) for words in words_ls]
    lda = models.ldamodel.LdaModel(corpus=corpus, id2word=dict, num_topics=3)

    topics = []
    for topic in lda.print_topics(num_words=5):
        print(topic)
        topics.append(topic[1].split('"')[1])
    # 推断每个语料库中的主题类别
    print(topics)
    dir_name = 'types/'
    files = [open(dir_name + i + '.txt', 'w', encoding='utf-8') for i in topics]

    # exit(0)
    cnt = defaultdict(int)
    weights = []
    y = []
    print('推断：')
    for e, values in enumerate(lda.inference(corpus)[0]):
        topic_val = 0
        topic_id = 0
        temp_val = []
        for tid, val in enumerate(values):
            temp_val.append(val)
            if val > topic_val:
                topic_val = val
                topic_id = tid
        weights.append(temp_val)
        y.append(topic_id)
        # print(topic_id, '->', words_ls[e])
        cnt[topic_id] += 1
        files[topic_id].write(lines[e].strip() + '\n')
    print(cnt)
    weights = np.array(weights)
    fig = plt.figure()
    ax = Axes3D(fig)
    ax.scatter(weights[:, 0], weights[:, 1], weights[:, 2], c=y)
    plt.show()
