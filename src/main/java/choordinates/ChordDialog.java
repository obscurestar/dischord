package choordinates;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChordDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<String> mListChords;
	private JTextField mTextName;
	private JTextField mTextAliases;
	private JTextField mTextIntervals;

	private ChoordData mChoordData = new ChoordData();
	
	private boolean mRefreshing = false;

	
	
	
	/**
	 * Create the dialog.
	 */
	
	public ChordDialog() {
		setTitle("Chord Dictionary");
		setBounds(100, 100, 490, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JPanel panelChordDict = new JPanel();
			contentPanel.add(panelChordDict);
			GridBagLayout gbl_panelChordDict = new GridBagLayout();
			gbl_panelChordDict.columnWidths = new int[] {150, 45, 0, 10};
			gbl_panelChordDict.rowHeights = new int[]{16, 0, 0,0,0,0};
			gbl_panelChordDict.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
			gbl_panelChordDict.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
			panelChordDict.setLayout(gbl_panelChordDict);
			{
				JLabel lblChordList = new JLabel("Chords");
				GridBagConstraints gbc_lblChordList = new GridBagConstraints();
				gbc_lblChordList.gridwidth = 2;
				gbc_lblChordList.insets = new Insets(0, 0, 5, 5);
				gbc_lblChordList.gridx = 0;
				gbc_lblChordList.gridy = 0;
				panelChordDict.add(lblChordList, gbc_lblChordList);
			}
			{
				mListChords = new JList<>();
				GridBagConstraints gbc_mListChords = new GridBagConstraints();
				gbc_mListChords.gridheight = 4;
				gbc_mListChords.gridwidth = 2;
				gbc_mListChords.insets = new Insets(0, 0, 5, 5);
				gbc_mListChords.fill = GridBagConstraints.BOTH;
				gbc_mListChords.gridx = 0;
				gbc_mListChords.gridy = 1;
				mListChords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	            mListChords.addListSelectionListener(new ListSelectionListener() {
	                @Override
	                public void valueChanged(ListSelectionEvent e) {
	                	if (!mRefreshing)
	                	{
		                    if (!e.getValueIsAdjusting()) { // Check if selection is finalized
		                    	mChoordData.setCurrentChord( mListChords.getSelectedIndex());
		                    	refresh();
		                    }
	                	}
	                }
	            });
				 {
				 	JLabel lblName = new JLabel("Name");
				 	GridBagConstraints gbc_lblName = new GridBagConstraints();
				 	gbc_lblName.insets = new Insets(0, 0, 5, 5);
				 	gbc_lblName.gridx = 2;
				 	gbc_lblName.gridy = 1;
				 	panelChordDict.add(lblName, gbc_lblName);
				 }
				 {
				 	mTextName = new JTextField();
				 	GridBagConstraints gbc_mTextName = new GridBagConstraints();
				 	gbc_mTextName.insets = new Insets(0, 0, 5, 5);
				 	gbc_mTextName.gridx = 3;
				 	gbc_mTextName.gridy = 1;
				 	panelChordDict.add(mTextName, gbc_mTextName);
				 	mTextName.setToolTipText("Name of the chord eg: Major, minor, dominant, 7th");
				 	mTextName.setColumns(10);
				 }

				 panelChordDict.add(mListChords, gbc_mListChords);
			}
			{
				JLabel lblAlias = new JLabel("Aliases");
				GridBagConstraints gbc_lblAlias = new GridBagConstraints();
				gbc_lblAlias.insets = new Insets(0, 0, 5, 5);
				gbc_lblAlias.gridx = 2;
				gbc_lblAlias.gridy = 2;
				panelChordDict.add(lblAlias, gbc_lblAlias);
				lblAlias.setToolTipText("Comma delimited list.  EG");
			}
			{
				mTextAliases = new JTextField();
				GridBagConstraints gbc_mTextAliases = new GridBagConstraints();
				gbc_mTextAliases.insets = new Insets(0, 0, 5, 5);
				gbc_mTextAliases.gridx = 3;
				gbc_mTextAliases.gridy = 2;
				panelChordDict.add(mTextAliases, gbc_mTextAliases);
				mTextAliases.setColumns(10);
			}
			{
				JLabel lblInterval = new JLabel("Intervals");
				GridBagConstraints gbc_lblInterval = new GridBagConstraints();
				gbc_lblInterval.insets = new Insets(0, 0, 5, 5);
				gbc_lblInterval.gridx = 2;
				gbc_lblInterval.gridy = 3;
				panelChordDict.add(lblInterval, gbc_lblInterval);
			}
			{
				mTextIntervals = new JTextField();
				GridBagConstraints gbc_mTextIntervals = new GridBagConstraints();
				gbc_mTextIntervals.insets = new Insets(0, 0, 5, 5);
				gbc_mTextIntervals.gridx = 3;
				gbc_mTextIntervals.gridy = 3;
				panelChordDict.add(mTextIntervals, gbc_mTextIntervals);
				mTextIntervals.setColumns(10);
			}
					
					
						JButton btnDelete = new JButton("Delete");
						GridBagConstraints gbc_btnDelete = new GridBagConstraints();
						gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
						gbc_btnDelete.gridx = 0;
						gbc_btnDelete.gridy = 5;
						panelChordDict.add(btnDelete, gbc_btnDelete);
						btnDelete.addActionListener(new ActionListener()
						{
		            @Override
		            public void actionPerformed(ActionEvent e)
		            {
		            	int id  = mChoordData.getCurrentChord();
		            	
		            	if (id == -1)
		            	{
		            		return;
		            	}
		            	
		            	String name = mChoordData.getChord(id).getName();
		            	
		            	if (confirm("Delete Chord", name))
		            	{
		            		mChoordData.deleteChord(id);
		            		mChoordData.setCurrentChord(-1); 
		            		refresh();
		            	}
		            }
						});
					
					
					JButton btnNew = new JButton("New");
					GridBagConstraints gbc_btnNew = new GridBagConstraints();
					gbc_btnNew.insets = new Insets(0, 0, 0, 5);
					gbc_btnNew.gridx = 1;
					gbc_btnNew.gridy = 5;
					panelChordDict.add(btnNew, gbc_btnNew);
					btnNew.addActionListener(new ActionListener()
					{
		            @Override
		            public void actionPerformed(ActionEvent e)
		            {
		            	if (saveChord(-1) )
		            	{
		            		mChoordData.setCurrentChord(mChoordData.getNumChords() - 1);
		            		refresh();
		            	}
		            }
		        });
				
				
					JButton btnSave = new JButton("Save");
					GridBagConstraints gbc_btnSave = new GridBagConstraints();
					gbc_btnSave.insets = new Insets(0, 0, 0, 5);
					gbc_btnSave.gridx = 2;
					gbc_btnSave.gridy = 5;
					panelChordDict.add(btnSave, gbc_btnSave);
							
							
								JButton btnCancel = new JButton("Cancel");
								GridBagConstraints gbc_btnCancel = new GridBagConstraints();
								gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
								gbc_btnCancel.gridx = 3;
								gbc_btnCancel.gridy = 5;
								panelChordDict.add(btnCancel, gbc_btnCancel);
								btnCancel.setActionCommand("Cancel");
						btnCancel.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e) {
		                closeWindow();
		            }
						});
				btnSave.addActionListener(new ActionListener()
				{
		            @Override
		            public void actionPerformed(ActionEvent e)
		            {
		            	int id = mChoordData.getCurrentChord();
		            	
		            	if (saveChord(id))
		            	{
		            		refresh();
		            	}
		            }
		        });
		}
		{
		}
		setVisible(true);
	}

	private void closeWindow()
	{
    	String name = mTextName.getText();
    	String aliases = mTextAliases.getText();
    	String intervals = mTextIntervals.getText();

    	int id = mChoordData.getCurrentChord();
    	
    	boolean do_dialog = false;
    	
    	if (id == -1)
    	{
    		if (name != "" || intervals != "" || aliases != "")
    		{
    			do_dialog = true;
    		}
    	}
    	else
    	{
    		if (name.compareTo( mChoordData.getChord(id).getName() ) != 0 
    				|| name.compareTo(makeIntervalsText(id)) != 0
    				|| aliases.compareTo( mChoordData.getChord(id).getAliasesString())!= 0)
    		{
    			do_dialog = true;
    		}
    	}
    	
    	if ( do_dialog )
    	{
    		//Ugly variable overloading!
    		do_dialog = !confirm("Unsaved Changes", "Confirm Close");
    	}
    	
    	if (!do_dialog)
    	{
    		this.dispose();
    	}
	}
	
	private boolean confirm(String title, String message)
	{
        int result = JOptionPane.showConfirmDialog(null, 
                                                  message, 
                                                  title, 
                                                  JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION)
        {
           return true;
        }
        /*else if (result == JOptionPane.CANCEL_OPTION)
        {
        	return false;
	    }*/
        return false;
	}
	
	private String makeIntervalsText(int id)
	{
		//Generates a space-delimited set of string names from chord id
		ArrayList<String> string_names= mChoordData.getChord(id).getAllNoteNames();
		
		return String.join(" ", string_names);
	}

	private void refresh()
	{
		//This list probably won't be that long.
		//Be lazy, just wipe it out and rebuild it.
		
		mRefreshing = true; //Combobox y u suk?
		
		mListChords.removeAll();
		
		//We could just return the arraylist from mChoordData
		//and convert it to a list, but why do anything the
		//easy way?
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (int i=0;i < mChoordData.getNumChords(); ++i) {
            listModel.addElement(mChoordData.getChord(i).getName());
        }

        // Set the list model to the JList
        mListChords.setModel(listModel);
		
		//Set Chord to current in data.
		int id = mChoordData.getCurrentChord();
		if (id > -1)
		{
			mListChords.setSelectedIndex(id);
			mTextName.setText(mChoordData.getChord(id).getName());
			mTextAliases.setText(mChoordData.getChord(id).getAliasesString());
			mTextIntervals.setText(makeIntervalsText(id));
		}
		else
		{
			mTextName.setText("");
			mTextAliases.setText("");
			mTextIntervals.setText("");
		}
	
		mRefreshing = false;
	}
	
	private boolean saveChord(int chord_id)
	{
    	String name = mTextName.getText();
    	String aliases = mTextAliases.getText();
    	String intervals = mTextIntervals.getText();

    	if (name.length() ==0)
    	{
    		JOptionPane.showMessageDialog(null, "Chord name is required.");
    		return false;
    	}
    	
       IntervalChord chord;
        
        try
        {
        	chord = IntervalChord.parse(intervals);
        }
        catch (IllegalArgumentException exception)
        {
        	JOptionPane.showMessageDialog(null, exception.getMessage());
        	return false;
        }
        
        chord.setName(name);
        chord.setAliasesFromString(aliases);
        
        if (chord_id == -1)
        {
        	//Add a new chord if using Add or save no chords present.
        	mChoordData.addChord(chord);
        	mChoordData.setCurrentChord(0);
        }
        else
        {
        	mChoordData.updateChord(chord_id,  chord);
        }
        return true;
	}
}
