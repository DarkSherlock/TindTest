package com.liang.tind.www.tindtest.activty;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liang.tind.www.tindtest.Dao.GameBeanDao;
import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.bean.GameBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by Administrator
 * <p>
 * date 2018/7/11
 */
public class TestGreenDao extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_query)
    Button mBtnQuery;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;
    @BindView(R.id.btn_insert)
    Button mBtnInsert;
    @BindView(R.id.btn_query_by_id)
    Button mBtnQueryById;
    @BindView(R.id.editText)
    EditText mEditText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_greendao;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);

        mBtnDelete.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnInsert.setOnClickListener(this);
        mBtnQueryById.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String id = mEditText.getText().toString().trim();

        switch (v.getId()) {
            case R.id.btn_delete:
                GameBeanDao.deleteById(Long.parseLong(id));
                Log.e(TAG, "onClick: delete id===" + id);
                break;
            case R.id.btn_insert:
                GameBean gameBean = new GameBean();
                gameBean.setId((Long.parseLong(id)));
                gameBean.setName("game" + id);
                gameBean.setVersion(1);
                gameBean.setUrl("url" + id);
                GameBeanDao.insert(gameBean);
                Log.e(TAG, "onClick: insert id ==" + id);
                break;
            case R.id.btn_update:
                GameBean gameBean1 = GameBeanDao.queryById(Long.parseLong(id));
                Log.e(TAG, "onClick: before update ");
                Log.e(TAG, gameBean1.toString());
                gameBean1.setVersion(2);
                GameBeanDao.update(gameBean1);
                break;
            case R.id.btn_query:
                List<GameBean> gameBeans = GameBeanDao.queryAll();
                for (int i = 0; i < gameBeans.size(); i++) {
                    Log.e(TAG, gameBeans.get(i).toString());
                }
                break;
            case R.id.btn_query_by_id:
                GameBean gameBeanQuery = GameBeanDao.queryById(Long.parseLong(id));
                Log.e(TAG, gameBeanQuery.toString());
                break;
        }
    }
}
