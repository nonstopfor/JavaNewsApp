# -*- coding: utf-8 -*-


from sklearn.feature_extraction.text import  TfidfVectorizer

from sklearn.cluster import DBSCAN
from sklearn.cluster import KMeans


from sklearn.manifold import TSNE
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from sys import argv

import jieba

import json


class TextCluster():
    def __init__(self,type="dbscan",num_labels=3,stopwords_path='vocab/stopwords.txt'):
        self.stopwords = self.load_stopwords(stopwords_path)
        self.tfidf_vectorizer = TfidfVectorizer(tokenizer=str.split,lowercase=True) 
        self.num_labels = num_labels
        self.dimension = 2
        self.type = type


    def load_stopwords(self, stopwords=None):
        """
        加载停用词
        :param stopwords:
        :return:
        """
        if stopwords:
            with open(stopwords, 'r', encoding='utf-8') as f:
                return [line.strip() for line in f]
        else:
            return []

    def preprocess_data(self, corpus_path):
        """
        文本预处理，每行一个文本
        :param corpus_path:
        :return:
        """
        corpus = []
        with open(corpus_path, 'r', encoding='utf-8') as f:
            for line in f:
                #corpus.append(' '.join([word for word in line if word not in self.stopwords]))
                new_line = ' '.join([word for word in jieba.lcut(line.strip()) if word not in self.stopwords])
                if len(new_line) >= 10:
                    corpus.append(new_line)
        return corpus

    def get_text_tfidf_matrix(self, corpus):
        """
        获取tfidf矩阵
        :param corpus:
        :return:
        """
        #tfidf = self.transformer.fit_transform(self.vectorizer.fit_transform(corpus))
        tfidf = self.tfidf_vectorizer.fit_transform(corpus)
        # 获取词袋中所有词语
        # words = self.vectorizer.get_feature_names()

        # 获取tfidf矩阵中权重
        weights = tfidf.toarray()
        return weights

    def reduction(self, weights):
        """
        PCA对数据进行降维
        :param weights:
        :param n_components:
        :return:
        """
        # pca = PCA(n_components=n_components)
        # return pca.fit_transform(weights)
        #改tsne
        tsne = TSNE(n_components=self.dimension)
        return tsne.fit_transform(weights)

    def doCluster(self, corpus_path, eps=0.1, min_samples=5):
        """
        DBSCAN：基于密度的文本聚类算法
        :param corpus_path: 语料路径，每行一个文本
        :param eps: DBSCA中半径参数
        :param min_samples: DBSCAN中半径eps内最小样本数目
        :param fig: 是否对降维后的样本进行画图显示
        :return:
        """
        corpus = self.preprocess_data(corpus_path)
        weights = self.get_text_tfidf_matrix(corpus)


        if self.type == 'dbscan':
            dbscan = DBSCAN(eps=eps, min_samples=min_samples)
            y = dbscan.fit_predict(weights)

        elif self.type == 'kmeans':
            kmeans = KMeans(n_clusters=num_labels, max_iter=300, n_init=40, init='k-means++')
            y = kmeans.fit_predict(weights)

        weights = self.reduction(weights)

        # 中心点
        # centers = clf.cluster_centers_

        # 用来评估簇的个数是否合适,距离约小说明簇分得越好,选取临界点的簇的个数
        # score = clf.inertia_

        # 每个样本所属的簇
        result = {"类别1":[],"类别2":[],"类别3":[]}
        for text_idx, label_idx in enumerate(y):
            #print('type of label = ',type(label_idx))
            label = "类别"+str(label_idx)
            text = ''.join(corpus[text_idx].split())
            if label not in result:
                result[label] = [text]
            else:
                result[label].append(text)

        with open('{}_result{}.json'.format(self.type,'_'+str(self.num_labels) if self.type=='kmeans' else ''),'w',encoding='utf-8') as file:
            json.dump(result,file,ensure_ascii=False,indent=2,separators=[',',':'])
        

        if self.dimension == 2:
            plt.scatter(weights[:, 0], weights[:, 1], c=y)
            plt.show()
        elif self.dimension == 3:
            fig = plt.figure()
            ax = Axes3D(fig)
            ax.scatter(weights[:,0],weights[:,1],weights[:,2],c=y)
            plt.show()
        
        return result


def run(cluster_type,num_labels):
    cluster = TextCluster(cluster_type,num_labels)
    cluster.doCluster('events.txt', eps=0.0005, min_samples=20)

if __name__ == '__main__':
    # #dbscan = TextCluster(type='dbscan')
    # kmeans = TextCluster(type='kmeans')
    
    # result = dbscan.dbscan('events.txt', eps=0.0005, min_samples=20)
    # #增大

    cluster_type = argv[1]
    num_labels = 3
    if len(argv)>=3:
        num_labels = int(argv[2])

    run(cluster_type,num_labels)