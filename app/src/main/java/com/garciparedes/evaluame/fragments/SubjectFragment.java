package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.cards.DetailsCard;
import com.garciparedes.evaluame.cards.PieChartCard;

import com.garciparedes.evaluame.cards.StatsCard;
import com.garciparedes.evaluame.cards.TestCard;
import com.garciparedes.evaluame.cards.TestListCard;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


/**
 * Created by garciparedes on 5/12/14.
 */
public class SubjectFragment extends Fragment {



    public static int subjectNum;

    private Subject subject;

    private FloatingActionButton button;


    public static SubjectFragment newInstance(int i) {
        SubjectFragment subjectFragment = new SubjectFragment();
        subjectNum = i;

        return subjectFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        subject = ListDB.get(subjectNum);
        button = (FloatingActionButton) view.findViewById(R.id.floating_button);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayList<Card> cards = new ArrayList<Card>();

        cards.add(new DetailsCard(getActivity(), subject));

        cards.add(new PieChartCard(getActivity(), subject));

        cards.add(new StatsCard(getActivity(), subject));

        cards.add(new TestListCard(getActivity(), subject));

        for (int i = 0; i < subject.getTestList().size(); i++) {
            // Create a Card
            TestCard card = new TestCard(getActivity(), subject.getTestElement(i));

            card.getCardHeader().setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
                @Override
                public void onButtonItemClick(Card card, View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, EditTestFragment.newInstance(subjectNum, ( (TestCard) card).getTest() ) ).commit();

                }
            });
            cards.add(card);
        }

        //Standard array
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);

        // Define your sections
        List<CardSection> sections =  new ArrayList<CardSection>();
        //sections.add(new CardSection(0,"Section 1"));
        //sections.add(new CardSection(3,"Section 2"));
        CardSection[] dummy = new CardSection[sections.size()];

        //Define your Sectioned adapter
        SectionedCardAdapter mAdapter = new SectionedCardAdapter(getActivity(), mCardArrayAdapter);
        mAdapter.setCardSections(sections.toArray(dummy));

        CardListView listView = (CardListView) getActivity().findViewById(R.id.subject_card_list);
        if (listView!=null){
            //Use the external adapter.
            listView.setExternalAdapter(mAdapter, mCardArrayAdapter);
        }

        //button.attachToListView(listView);
        //button.bringToFront();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, AddTestFragment.newInstance(subjectNum))
                        .commit();

            }
        });
    }



    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_subject) {

            //deleteSubject();

            return true;
        }

        if (item.getItemId() == R.id.action_edit_subject) {

            getFragmentManager().beginTransaction()
                    .replace(R.id.container, EditSubjectFragment.newInstance(subject))
                    .commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

}