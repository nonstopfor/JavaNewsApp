import jieba
from gensim import corpora, models

with open('vocab/stopwords.txt', encoding='utf-8') as f:
    stopwords = [line.strip() for line in f.readlines()]
    stopwords.append(" ")

def remove_stopwords(ls):  # 去除停用词
    return [word for word in ls if word not in stopwords]


with open('events.txt', 'r', encoding='utf-8') as f:
    lines = f.readlines()
    words_ls = []
    for line in lines:
        words_ls.append(remove_stopwords(jieba.lcut(line.strip())))

    dict = corpora.Dictionary(words_ls)
    corpus = [dict.doc2bow(words) for words in words_ls]
    lda = models.ldamodel.LdaModel(corpus=corpus, id2word=dict, num_topics=3)

    for topic in lda.print_topics(num_words=5):
        print(topic)

    # 推断每个语料库中的主题类别

    # exit(0)
    print('推断：')
    for e, values in enumerate(lda.inference(corpus)[0]):
        topic_val = 0
        topic_id = 0
        for tid, val in enumerate(values):
            if val > topic_val:
                topic_val = val
                topic_id = tid
        print(topic_id, '->', words_ls[e])
        break
