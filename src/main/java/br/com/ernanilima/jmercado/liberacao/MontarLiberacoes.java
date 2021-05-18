package br.com.ernanilima.jmercado.liberacao;

import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MontarLiberacoes {

    private final CheckBoxTreeItem<Liberacoes> ROOT = new CheckBoxTreeItem<>(Liberacoes.ADMIN);
    private final List<CheckBoxTreeItem<Liberacoes>> LS_MENUS = new ArrayList<>();

    private Set<TreeItem<Liberacoes>> SET_TREE;

    private void criarMenus(Set<TreeItem<Liberacoes>> selecionado) {
        for (Liberacoes liberacao1 : Liberacoes.obterLiberacoes()) {
            if (liberacao1.getCodigo() != ROOT.getValue().getCodigo() &&
                    liberacao1.getCodigoPai() == ROOT.getValue().getCodigo()) {
                CheckBoxTreeItem<Liberacoes> menu1 = new CheckBoxTreeItem<>(liberacao1);
                menu1.setIndependent(true);
                menu1.setSelected(selecionado.stream().anyMatch(x -> x.getValue().getCodigo() == liberacao1.getCodigo()));
                if (menu1.isSelected()) SET_TREE.add(menu1);

                for (Liberacoes liberacao2 : Liberacoes.obterLiberacoes()) {
                    if (liberacao2.getCodigoPai() == menu1.getValue().getCodigo()) {
                        CheckBoxTreeItem<Liberacoes> menu2 = new CheckBoxTreeItem<>(liberacao2);
                        menu2.setIndependent(true);
                        menu2.setSelected(selecionado.stream().anyMatch(x -> x.getValue().getCodigo() == liberacao2.getCodigo()));
                        if (menu2.isSelected()) SET_TREE.add(menu2);

                        for (Liberacoes liberacao3 : Liberacoes.obterLiberacoes()) {
                            if (liberacao3.getCodigoPai() == menu2.getValue().getCodigo()) {
                                CheckBoxTreeItem<Liberacoes> menu3 = new CheckBoxTreeItem<>(liberacao3);
                                menu3.setIndependent(true);
                                menu3.setSelected(selecionado.stream().anyMatch(x -> x.getValue().getCodigo() == liberacao3.getCodigo()));
                                if (menu3.isSelected()) SET_TREE.add(menu3);

                                for (Liberacoes liberacao4 : Liberacoes.obterLiberacoes()) {
                                    if (liberacao4.getCodigoPai() == menu3.getValue().getCodigo()) {
                                        CheckBoxTreeItem<Liberacoes> menu4 = new CheckBoxTreeItem<>(liberacao4);
                                        menu4.setIndependent(true);
                                        menu4.setSelected(selecionado.stream().anyMatch(x -> x.getValue().getCodigo() == liberacao4.getCodigo()));
                                        if (menu4.isSelected()) SET_TREE.add(menu4);
                                        menu3.getChildren().add(menu4);

                                    }
                                }
                                menu2.getChildren().add(menu3);
                            }
                        }
                        menu1.getChildren().add(menu2);
                    }
                }
                LS_MENUS.add(menu1);
            }
        }

        selecionado.clear();
        selecionado.addAll(SET_TREE);

    }

    private void criarMenusEvent(Set<TreeItem<Liberacoes>> selecionado) {
        ROOT.addEventHandler(CheckBoxTreeItem.checkBoxSelectionChangedEvent(), (CheckBoxTreeItem.TreeModificationEvent<Liberacoes> evt) -> {
            CheckBoxTreeItem<Liberacoes> item = evt.getTreeItem();

            if (evt.wasSelectionChanged()) {
                if (item.isSelected()) {
                    selecionado.add(item);
                } else {
                    selecionado.remove(item);
                }
            }
        });
    }

    public CheckBoxTreeItem<Liberacoes> getMenus(Set<TreeItem<Liberacoes>> selecionado) {
        SET_TREE = new HashSet<>();
        criarMenus(selecionado);
        criarMenusEvent(selecionado);
        ROOT.getChildren().addAll(LS_MENUS);
        ROOT.setIndependent(true);
        return ROOT;
    }
}
