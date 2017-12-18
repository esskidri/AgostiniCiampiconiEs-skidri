package com.example.ago.travlendarandroidclient.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ago.travlendarandroidclient.R;
import com.example.ago.travlendarandroidclient.modelB.StepperTransportSolution;
import com.example.ago.travlendarandroidclient.modelB.TransportSolutionClient;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;


public class VerticalStepperAdapterDemoFragment extends Fragment implements IStepperAdapter {

    private VerticalStepperView mVerticalStepperView;
    private StepperTransportSolution sol = new TransportSolutionClient();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vertical_stepper_adapter_demo, parent, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mVerticalStepperView = view.findViewById(R.id.vertical_stepper_view);
        mVerticalStepperView.setStepperAdapter(this);
        mVerticalStepperView.setActivatedColor(getResources().getColor(R.color.colorPrimaryDark));
        mVerticalStepperView.setNormalColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public @NonNull
    CharSequence getTitle(int index) {
        if (index == 0) {
            return "Start: " + sol.getTransportSegment().get(0).getName();
        }
        if (index == sol.getTransportSegment().size() - 1) {
            return "Finish: " + sol.getTransportSegment().get(index).getName();
        }
        return "Step:" + sol.getTransportSegment().get(index).getAdressA();
    }

    @Override
    public @Nullable
    CharSequence getSummary(int index) {
        String str = "<b>" + sol.getTransportSegment().get(0).getAdressA() + "</b>";

        switch (index) {
            case 0:
                return Html.fromHtml("You are started from "
                        + (mVerticalStepperView.getCurrentStep() > index ? str : ""));
            default:
                return null;

        }
    }

    @Override
    public int size() {
        return sol.getTransportSegment().size();
    }

    @Override
    public View onCreateCustomView(final int index, Context context, VerticalStepperItemView parent) {
        View inflateView = LayoutInflater.from(context).inflate(R.layout.vertical_stepper_sample_item, parent, false);
        TextView contentView = inflateView.findViewById(R.id.item_content);
        contentView.setText(generateContent(index)
                   /* index == 0 ? R.string.content_step_0 : (index == sol.getTransportSegment().size()-1 ? R.string.content_step_1 : R.string.content_step_2)*/
        );
        Button nextButton = inflateView.findViewById(R.id.button_next);
        nextButton.setText(index == size() - 1 ? "Finish" : "Next >");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mVerticalStepperView.nextStep()) {
                    mVerticalStepperView.setErrorText(0, mVerticalStepperView.getErrorText(0) == null ? "Test error" : null);
                    Snackbar.make(mVerticalStepperView, "Set!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        Button prevButton = inflateView.findViewById(R.id.button_prev);
        prevButton.setText(index == 0 ? "More >" : "< Back");
        if (index == 0) {
            prevButton.setVisibility(View.INVISIBLE);
        }
        inflateView.findViewById(R.id.button_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index != 0) {
                    mVerticalStepperView.prevStep();
                } else {
                    mVerticalStepperView.setAnimationEnabled(!mVerticalStepperView.isAnimationEnabled());
                }
            }
        });
        //
        //    mVerticalStepperView.setNormalColor(getResources().getColor(R.color.colorPrimaryDark));
        return inflateView;
    }

    @Override
    public void onShow(int index) {

    }

    @Override
    public void onHide(int index) {

    }

    private String generateContent(int index) {
        if (index == sol.getTransportSegment().size() - 1) return "Well Done!";
        String s = "";
        if (index == 0) {
            s = sol.getTransportSegment().get(index).getAdressA() + " --> " + sol.getTransportSegment().get(index).getAdressB() + "\n";
        } else {
            s = "road to: " + sol.getTransportSegment().get(index).getAdressB() + "\n";
        }
        SpannableString spannableString = new SpannableString("@");
        Drawable d = getResources().getDrawable(R.drawable.ic_about_us);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(span, spannableString.toString().indexOf("@"), spannableString.toString().indexOf("@") + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        s += "\n" + spannableString + sol.getTransportSegment().get(index).cost() + " €";
        s += "\nDistance: " + sol.getTransportSegment().get(index).distance() + " km";
        s += "\nMean: " + sol.getTransportSegment().get(index).mean();

        return s;

    }

}
