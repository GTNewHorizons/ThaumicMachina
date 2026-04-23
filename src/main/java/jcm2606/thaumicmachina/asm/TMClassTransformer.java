
package jcm2606.thaumicmachina.asm;

import java.util.Iterator;
import java.util.ListIterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TMClassTransformer implements IClassTransformer {

    String itemStackClassPath;

    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("thaumcraft.common.items.wands.ItemWandCasting")) {
            this.itemStackClassPath = ItemStack.class.getName()
                .replace(".", "/");
            TMFMLLoadingPlugin.log("Found Thaumcraft base class 'ItemWandCasting', patching class.", "INFO");
            basicClass = this.patchItemWandCastingClass(name, basicClass);
        }
        if (name.equals("thaumcraft.client.renderers.models.gear.ModelWand")
            || name.equals("thaumcraft.client.renderers.models.ModelWand")) {
            TMFMLLoadingPlugin.log("Found Thaumcraft base class 'ModelWand', patching class.", "INFO");
            basicClass = this.patchModelWandClass(name, basicClass);
        }
        return basicClass;
    }

    public byte[] patchItemWandCastingClass(String className, byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept((ClassVisitor) node, 0);
        Iterator methodIterator = node.methods.iterator();
        TMFMLLoadingPlugin.log("Inside 'ItemWandCasting' class, searching for methods...", "INFO");
        while (methodIterator.hasNext()) {
            InsnList injectionList;
            int i;
            ListIterator iterator;
            AbstractInsnNode targetNode;
            AbstractInsnNode currNode;
            MethodNode mnode = (MethodNode) methodIterator.next();
            int index = -1;
            if (mnode.name.equals("getMaxVis") && mnode.desc.equals("(L" + this.itemStackClassPath + ";)I")) {
                TMFMLLoadingPlugin.log("Discovered 'getMaxVis' method, patching method...", "INFO");
                currNode = null;
                targetNode = null;
                iterator = mnode.instructions.iterator();
                i = -1;
                while (iterator.hasNext()) {
                    ++i;
                    currNode = (AbstractInsnNode) iterator.next();
                    if (currNode.getOpcode() != 153) continue;
                    targetNode = currNode;
                    index = i - 6;
                }
                mnode.instructions.remove(mnode.instructions.get(index + 14));
                mnode.instructions.remove(mnode.instructions.get(index + 13));
                mnode.instructions.remove(mnode.instructions.get(index + 12));
                mnode.instructions.remove(mnode.instructions.get(index + 11));
                mnode.instructions.remove(mnode.instructions.get(index + 10));
                mnode.instructions.remove(mnode.instructions.get(index + 9));
                mnode.instructions.remove(mnode.instructions.get(index + 8));
                mnode.instructions.remove(mnode.instructions.get(index + 7));
                mnode.instructions.remove(mnode.instructions.get(index + 6));
                mnode.instructions.remove(mnode.instructions.get(index + 5));
                mnode.instructions.remove(mnode.instructions.get(index + 4));
                mnode.instructions.remove(mnode.instructions.get(index + 3));
                mnode.instructions.remove(mnode.instructions.get(index + 2));
                mnode.instructions.remove(mnode.instructions.get(index + 1));
                mnode.instructions.remove(mnode.instructions.get(index - 1));
                injectionList = new InsnList();
                injectionList.add(
                    (AbstractInsnNode) new MethodInsnNode(
                        184,
                        "jcm2606/thaumicmachina/wand/WandAugmentationHandler",
                        "handleMaxVisMethod",
                        "(L" + this.itemStackClassPath + ";)I"));
                mnode.instructions.insert(mnode.instructions.get(index - 1), injectionList);
                TMFMLLoadingPlugin.log("Patched method.", "INFO");
            }
            if (mnode.name.equals("hasRunes") && mnode.desc.equals("(L" + this.itemStackClassPath + ";)Z")) {
                TMFMLLoadingPlugin.log("Discovered 'hasRunes' method, patching method...", "INFO");
                currNode = null;
                targetNode = null;
                iterator = mnode.instructions.iterator();
                i = -1;
                while (iterator.hasNext()) {
                    ++i;
                    currNode = (AbstractInsnNode) iterator.next();
                    if (currNode.getOpcode() != 3) continue;
                    targetNode = currNode;
                    index = i - 1;
                }
                mnode.instructions.remove(mnode.instructions.get(index + 1));
                injectionList = new InsnList();
                injectionList.add((AbstractInsnNode) new VarInsnNode(25, 1));
                injectionList.add(
                    (AbstractInsnNode) new MethodInsnNode(
                        184,
                        "jcm2606/thaumicmachina/wand/WandAugmentationHandler",
                        "handleHasRunesMethod",
                        "(L" + this.itemStackClassPath + ";)Z"));
                mnode.instructions.insert(mnode.instructions.get(index), injectionList);
                TMFMLLoadingPlugin.log("Patched method.", "INFO");
            }
            if (!mnode.name.equals("getConsumptionModifier") || !mnode.desc.equals(
                "(L" + this.itemStackClassPath
                    + ";"
                    + "L"
                    + EntityPlayer.class.getName()
                        .replace(".", "/")
                    + ";Lthaumcraft/api/aspects/Aspect;Z)F"))
                continue;
            TMFMLLoadingPlugin.log("Discovered 'getConsumptionModifier' method, patching method...", "INFO");
            currNode = null;
            targetNode = null;
            iterator = mnode.instructions.iterator();
            i = -1;
            while (iterator.hasNext()) {
                ++i;
                currNode = (AbstractInsnNode) iterator.next();
                if (currNode.getOpcode() != 153) continue;
                targetNode = currNode;
                index = i + 2;
                break;
            }
            mnode.instructions.remove(mnode.instructions.get(index + 4));
            mnode.instructions.remove(mnode.instructions.get(index + 3));
            mnode.instructions.remove(mnode.instructions.get(index + 2));
            mnode.instructions.remove(mnode.instructions.get(index + 1));
            injectionList = new InsnList();
            injectionList.add((AbstractInsnNode) new VarInsnNode(25, 1));
            injectionList.add((AbstractInsnNode) new InsnNode(4));
            injectionList.add(
                (AbstractInsnNode) new MethodInsnNode(
                    184,
                    "jcm2606/thaumicmachina/wand/WandAugmentationHandler",
                    "handleConsumptionModifierMethod",
                    "(L" + this.itemStackClassPath + ";Z)F"));
            mnode.instructions.insert(mnode.instructions.get(index), injectionList);
            iterator = mnode.instructions.iterator();
            i = -1;
            while (iterator.hasNext()) {
                ++i;
                currNode = (AbstractInsnNode) iterator.next();
                if (!(currNode instanceof FrameNode)) continue;
                targetNode = currNode;
                index = i;
                break;
            }
            mnode.instructions.remove(mnode.instructions.get(index + 4));
            mnode.instructions.remove(mnode.instructions.get(index + 3));
            mnode.instructions.remove(mnode.instructions.get(index + 2));
            mnode.instructions.remove(mnode.instructions.get(index + 1));
            injectionList = new InsnList();
            injectionList.add((AbstractInsnNode) new VarInsnNode(25, 1));
            injectionList.add((AbstractInsnNode) new InsnNode(3));
            injectionList.add(
                (AbstractInsnNode) new MethodInsnNode(
                    184,
                    "jcm2606/thaumicmachina/wand/WandAugmentationHandler",
                    "handleConsumptionModifierMethod",
                    "(L" + this.itemStackClassPath + ";Z)F"));
            mnode.instructions.insert(mnode.instructions.get(index), injectionList);
            TMFMLLoadingPlugin.log("Patched method.", "INFO");
        }
        TMFMLLoadingPlugin.log("Patched class.", "INFO");
        ClassWriter writer = new ClassWriter(3);
        node.accept((ClassVisitor) writer);
        return writer.toByteArray();
    }

    public byte[] patchModelWandClass(String className, byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept((ClassVisitor) node, 0);
        Iterator methodIterator = node.methods.iterator();
        TMFMLLoadingPlugin.log("Inside 'ModelWand' class, searching for methods...", "INFO");
        while (methodIterator.hasNext()) {
            MethodNode mnode = (MethodNode) methodIterator.next();
            int index = -1;
            if (!mnode.name.equals("render") || !mnode.desc.equals("(L" + this.itemStackClassPath + ";)V")) continue;
            TMFMLLoadingPlugin.log("Discovered 'render' method, patching method...", "INFO");
            AbstractInsnNode currNode = null;
            AbstractInsnNode targetNode = null;
            ListIterator iterator = mnode.instructions.iterator();
            int i = -1;
            while (iterator.hasNext()) {
                double d;
                ++i;
                currNode = (AbstractInsnNode) iterator.next();
                if (!(currNode instanceof LdcInsnNode)) continue;
                LdcInsnNode ldcNode = (LdcInsnNode) currNode;
                if (!(ldcNode.cst instanceof Double) || (d = ((Double) ldcNode.cst).doubleValue()) != (double) -0.01f)
                    continue;
                targetNode = currNode;
                index = i - 8;
            }
            mnode.instructions.remove(mnode.instructions.get(index + 2));
            InsnList injectionList = new InsnList();
            injectionList.add((AbstractInsnNode) new VarInsnNode(25, 1));
            injectionList.add(
                (AbstractInsnNode) new MethodInsnNode(
                    184,
                    "jcm2606/thaumicmachina/wand/WandAugmentationHandler",
                    "handleRuneRendererDouble",
                    "(L" + this.itemStackClassPath + ";)D"));
            mnode.instructions.insert(mnode.instructions.get(index + 1), injectionList);
            mnode.instructions.remove(mnode.instructions.get((index -= 14) + 1));
            injectionList = new InsnList();
            injectionList.add((AbstractInsnNode) new VarInsnNode(25, 1));
            injectionList.add(
                (AbstractInsnNode) new MethodInsnNode(
                    184,
                    "jcm2606/thaumicmachina/wand/WandAugmentationHandler",
                    "handleRuneRendererPasses",
                    "(L" + this.itemStackClassPath + ";)I"));
            mnode.instructions.insert(mnode.instructions.get(index), injectionList);
            TMFMLLoadingPlugin.log("Patched method.", "INFO");
            break;
        }
        TMFMLLoadingPlugin.log("Patched class.", "INFO");
        ClassWriter writer = new ClassWriter(3);
        node.accept((ClassVisitor) writer);
        return writer.toByteArray();
    }
}
