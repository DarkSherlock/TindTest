package com.liang.tind.www.tindtest.Dao;

import com.liang.tind.www.tindtest.base.MyApplicationLike;
import com.liang.tind.www.tindtest.bean.GameBean;

import java.util.List;

/**
 * Created by handsome on 2016/4/19.
 */
public class GameBeanDao {

    /**
     * 添加数据
     *
     * @param game
     */
    public static void insert(GameBean game) {
        MyApplicationLike.getDaoSession().getGameBeanDao().insertOrReplace(game);
    }

    /**
     * 批量添加数据
     *
     * @param  games 游戏集合
     */
    public static void insertAll(List<GameBean> games) {
        MyApplicationLike.getDaoSession().getGameBeanDao().insertOrReplaceInTx(games);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteById(long id) {
        MyApplicationLike.getDaoSession().getGameBeanDao().delete(queryById(id));
    }

    /**
     * 更新数据
     *
     * @param gameBean
     */
    public static void update(GameBean gameBean) {
        MyApplicationLike.getDaoSession().getGameBeanDao().update(gameBean);
    }

    /**
     * 查找特定id的数据
     *
     * @return GameBean
     */
    public static GameBean queryById(long id) {
        return MyApplicationLike.getDaoSession().getGameBeanDao().queryBuilder().where(com.liang.tind.www.tindtest.bean.GameBeanDao.Properties.Id.eq(id)).build().unique();
    }

    /**
     * 查询所有数据
     *
     * @return List<GameBean> arrayList
     */
    public static List<GameBean> queryAll() {
        return MyApplicationLike.getDaoSession().getGameBeanDao().loadAll();
    }
}
