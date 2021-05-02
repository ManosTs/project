package apackage.redblacktree.date;

import java.util.Date;

interface IRedBlackTreeDate {
    void insert(Record data);
    void printTree();
    void search(Date key);
    void modifyVolume(Date key);
    void deletion(Date key);
}
