package com.example.hellochart2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {

	private LineChartView lineChartView;		
	private LineChartData lineChartData;		//����ͼ��ʾ�����ݣ����������ϵĵ㣩
    private List<Line> linesList;				
    private List<PointValue> points;			//Ҫ��ʾ�ĵ�
    private List<PointValue> pointValueList;	
    private int position = 0;					
    private Timer timer;						//��ʱˢ������ͼ
    private boolean isFinish = false;

    private Axis axisX;							//X��
    private Axis axisY;							//Y��
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initAxisView();							//��ʼ��������
		showMovingLineChart();					//��̬��ʾ���߱仯
	}

	/**
	 * ��ʼ����ʾ������
	 */
	private void initAxisView() {
		
		lineChartView = (LineChartView) findViewById(R.id.line_chart);
        pointValueList = new ArrayList<PointValue>();
        linesList = new ArrayList<Line>();

        /** ��ʼ��Y�� */
        axisY = new Axis();
        axisY.setName("Ũ�ȣ���λ��XX��");						//���Y�������
        axisY.setHasLines(true);							//Y��ָ���
        axisY.setTextSize(10);								//���������С
//        axisY.setTextColor(Color.parseColor("#AFEEEE"));	//����Y����ɫ��Ĭ��ǳ��ɫ
        lineChartData = new LineChartData(linesList);
        lineChartData.setAxisYLeft(axisY);					//����Y�������
        
        /** ��ʼ��X�� */
        axisX = new Axis();
        axisX.setHasTiltedLabels(false);  					//X������������б����ʾ����ֱ�ģ�true��б����ʾ   
//        axisX.setTextColor(Color.CYAN);  					//����X����ɫ
        axisX.setName("ʱ�䣨��λ��s��");  						//X������
        axisX.setHasLines(true);							//X��ָ���
        axisX.setTextSize(10);								//���������С
        axisX.setMaxLabelChars(1); 							//����0�Ļ�X������ֵ�ͼ��Ϊ1
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
        for (int i = 0; i < 61; i++) {    
            mAxisXValues.add(new AxisValue(i).setLabel(i+""));    
        }
        axisX.setValues(mAxisXValues);  					//���X�����������
        lineChartData.setAxisXBottom(axisX); 				//X���ڵײ�  
      
        lineChartView.setLineChartData(lineChartData);
        
        Viewport port = initViewPort(0,10);					//��ʼ��X��10���������
        lineChartView.setCurrentViewportWithAnimation(port);
        lineChartView.setInteractive(false);				//���ò��ɽ���
        lineChartView.setScrollEnabled(true);				
        lineChartView.setValueTouchEnabled(false);			
        lineChartView.setFocusableInTouchMode(false);		
        lineChartView.setViewportCalculationEnabled(false);
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.startDataAnimation();
        
        loadData();											//���ش���ʾ����
	}
	
    private Viewport initViewPort(float left,float right) {
        Viewport port = new Viewport();
        port.top = 100;				//Y�����ޣ��̶�(���̶������޵Ļ���Y������ֵ������Ӧ�仯)
        port.bottom = 0;			//Y�����ޣ��̶�
        port.left = left;			//X����߽磬�仯
        port.right = right;			//X���ұ߽磬�仯
        return port;
    }
    
    /**
     * ��ʼ�����ݵ�
     */
	private void loadData() {
		points = new ArrayList<PointValue>();
		for (int i = 0; i < 100; i++) {
			points.add(new PointValue(i + 1, i % 5 * 10 + 30));
		}
	}

	/**
	 * ���ݵ㶯̬ˢ��
	 */
	private void showMovingLineChart() {
		
		timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!isFinish){
                    pointValueList.add(points.get(position));		//ʵʱ����µĵ�
                    
                    //�����µĵ�ļ��ϻ����µ���
                    Line line = new Line(pointValueList);
                    line.setColor(Color.parseColor("#FFCD41"));		//����������ɫ
                    line.setShape(ValueShape.CIRCLE);				//��������ͼ�����ݵ���״Ϊ Բ�� ���������� ��ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND��
                    line.setCubic(false);							//�����Ƿ�ƽ����true��ƽ�����ߣ�false������
                    line.setHasLabels(true);						//�����Ƿ��б�ע
//                    line.setHasLabelsOnlyForSelected(true);		//�������������ʾ����,������line.setHasLabels(true);֮������Ч
                    line.setHasLines(true);							//�Ƿ�������ʾ�����Ϊfalse��û������ֻ�е���ʾ 
                    line.setHasPoints(true);						//�Ƿ���ʾԲ�� �����Ϊfalse��û��ԭ��ֻ�е���ʾ��ÿ�����ݵ㶼�Ǹ���Բ�㣩
                    
                    linesList.add(line);
                    lineChartData = new LineChartData(linesList);
                    lineChartData.setAxisYLeft(axisY);					//����Y������
                    lineChartData.setAxisXBottom(axisX); 				//X���ڵײ� 
                    lineChartView.setLineChartData(lineChartData);
                    
                    float xAxisValue = points.get(position).getX();
                    //���ݵ�ĺ�����ʵʱ�任X���������ͼ��Χ
                    Viewport port;
                    if(xAxisValue > 10){
                        port = initViewPort(xAxisValue - 10,xAxisValue);
                    }
                    else {
                        port = initViewPort(0,10);
                    }
                    lineChartView.setMaximumViewport(port);
                    lineChartView.setCurrentViewport(port);

                    position++;
                    
                    if(position > points.size()-1) {
                        isFinish = true;
                    }
                }
            }
        },300,300);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
	}
	
	
}
